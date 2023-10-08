package com.example.demobank.service.view;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.User;
import com.example.demobank.entity.transaction.Transaction;
import com.example.demobank.util.mail_bot.VerificationEmail;
import com.example.demobank.util.SecurityUtils;
import com.example.demobank.util.mail_bot.MailBot;
import com.example.demobank.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ViewServiceImpl implements ViewService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final Environment environment;

    public ViewServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, UserRepository userRepository, Environment environment) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.environment = environment;
    }

    @Override
    public ModelAndView getLoginView() {
        ModelAndView loginPageView =  new ModelAndView("login");
        loginPageView.addObject("token", SecurityUtils.generateToken());
        loginPageView.addObject("PageTitle", "Login");

        return loginPageView;
    }

    @Override
    public ModelAndView getRegisterView() {
        ModelAndView registerPageView =  new ModelAndView("register");
        registerPageView.addObject("PageTitle", "Register");
        return registerPageView;
    }

    @Override
    public ModelAndView getDashboardView(HttpSession session) {
        ModelAndView dashboardPageView = new ModelAndView("dashboard");
        User user = (User) session.getAttribute("user");

        List<Account> userAccounts = accountRepository.findByUser(user);
        dashboardPageView.addObject("userAccounts", userAccounts);

        BigDecimal totalAccountsBalance = accountRepository.getTotalAccountsBalanceByUser(user);
        dashboardPageView.addObject("totalBalance", totalAccountsBalance);

        return dashboardPageView;
    }

    @Override
    public ModelAndView getTransactionHistoryView(HttpSession session) {
        ModelAndView transactionHistoryPageView = new ModelAndView("transaction_history");
        User user = (User) session.getAttribute("user");

        List<Transaction> payments = transactionRepository.findAllPaymentsCommittedByOrToUserByUser(user);
        transactionHistoryPageView.addObject("payments", payments);

        List<Transaction> deposits = transactionRepository.findAllDepositsCommittedByUser(user);
        transactionHistoryPageView.addObject("deposits", deposits);

        List<Transaction> transfers = transactionRepository.findAllTransfersCommittedByUser(user);
        transactionHistoryPageView.addObject("transfers", transfers);

        List<Transaction> withdraws = transactionRepository.findAllWithdrawsCommittedByUser(user);
        transactionHistoryPageView.addObject("withdraws", withdraws);

        return transactionHistoryPageView;
    }

    @Override
    public ModelAndView getIndexView() {
        ModelAndView indexPageView =  new ModelAndView("index");
        indexPageView.addObject("PageTitle", "Home");
        return indexPageView;
    }

    @Override
    public ModelAndView getErrorView() {
        ModelAndView errorPageView =  new ModelAndView("error");
        errorPageView.addObject("PageTitle", "Error");
        return errorPageView;
    }

    @Override
    public ModelAndView getLoginWithVerificationView(String token, String code) {
        ModelAndView loginPageWithVerificationView = this.getLoginView();



        boolean isUserTokenExistsInDatabase = userRepository.existsByToken(token);
        if (!isUserTokenExistsInDatabase) {
            ModelAndView errorPageView = new ModelAndView("error");
            errorPageView.addObject("PageTitle", "Срок действия сеанса истёк");
            return errorPageView;
        }
        userRepository.updateVerifiedStatusOfUserByTokenAndCode(token, code);
        loginPageWithVerificationView.addObject("success", "Аккаунт успешно подтвержден, теперь в него можно войти");
        return loginPageWithVerificationView;
    }

    @Override
    public ModelAndView getRegisterWithSuccessView(User user, BindingResult result, String firstName, String lastName, String email, String password, String confirmPassword) {
        ModelAndView registrationPageView = new ModelAndView("register");

        boolean isUserAlreadyRegistered = userRepository.existsByEmail(email);

        if (isUserAlreadyRegistered) {
            registrationPageView.addObject("emailAlreadyOccupied", "Введённый адрес электронной почты уже занят");
            return registrationPageView;
        }

        if(result.hasErrors() && confirmPassword.isEmpty()) {
            registrationPageView.addObject("confirm_pass", "Подтвердите введённый пароль");
            return registrationPageView;
        }

        if (!password.equals(confirmPassword)) {
            registrationPageView.addObject("passwordMisMatch", "Введённые пароли не совпадают");
            return registrationPageView;
        }

        String token = SecurityUtils.generateToken();
        String code = SecurityUtils.generateCode();

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User userToRegister = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(hashedPassword)
                .token(token)
                .code(code)
                .build();
        userRepository.save(userToRegister);

        String emailFrom = environment.getProperty("mail.configuration.sender.email");
        String emailText = VerificationEmail.generateText(token, code);
        MailBot.sendEmailMessage(emailFrom, email, "Подтверждение аккаунта", emailText);

        registrationPageView.addObject("success", "Аккаунт успешно зарегистрирован, проверьте вашу почту и подтвердите его");

        return registrationPageView;
    }

}
