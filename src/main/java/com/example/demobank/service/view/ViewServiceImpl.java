package com.example.demobank.service.view;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.User;
import com.example.demobank.util.mail_bot.VerificationEmail;
import com.example.demobank.util.Token;
import com.example.demobank.util.mail_bot.MailBot;
import com.example.demobank.repository.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class ViewServiceImpl implements ViewService {

    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final DepositRepository depositRepository;
    private final TransferRepository transferRepository;
    private final WithdrawRepository withdrawRepository;
    private final UserRepository userRepository;

    public ViewServiceImpl(AccountRepository accountRepository, PaymentRepository paymentRepository, DepositRepository depositRepository, TransferRepository transferRepository, WithdrawRepository withdrawRepository, UserRepository userRepository) {this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
        this.depositRepository = depositRepository;
        this.transferRepository = transferRepository;
        this.withdrawRepository = withdrawRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ModelAndView getLoginView() {
        ModelAndView loginPageView =  new ModelAndView("login");
        loginPageView.addObject("token", Token.generate());
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

        //List<Payment> payments = paymentRepository.findAllPaymentsWhereUserIsSenderOrReceiverByUser(user);
        //transactionHistoryPageView.addObject("payments", payments);

        //List<Deposit> deposits = depositRepository.findByAccount_User(user);
        //transactionHistoryPageView.addObject("deposits", deposits);

        //List<Transfer> transfers = transferRepository.findByAccountFrom_User();
        //transactionHistoryPageView.addObject("transfers", transfers);

        //List<Withdraw> withdraws = withdrawRepository.findByUser_id(user.getId());
        //transactionHistoryPageView.addObject("withdraws", withdraws);

        return transactionHistoryPageView;
    }

    @Override
    public ModelAndView getIndexView() {
        ModelAndView getIndexPage =  new ModelAndView("index");
        getIndexPage.addObject("PageTitle", "Home");
        return getIndexPage;
    }

    @Override
    public ModelAndView getErrorView() {
        ModelAndView getErrorPage =  new ModelAndView("error");
        getErrorPage.addObject("PageTitle", "Error");
        return getErrorPage;
    }

    @Override
    public ModelAndView getLoginWithVerificationView(String token, String code) {
        ModelAndView loginPageWithVerificationView = this.getLoginView();

        String dbToken = userRepository.checkToken(token);
        if (dbToken == null || dbToken.isEmpty()) {
            ModelAndView getErrorPage = new ModelAndView("error");
            getErrorPage.addObject("PageTitle", "Срок действия сеанса истёк");
            return getErrorPage;
        }
        userRepository.updateVerifiedAndVerified_atAndUpdated_atByTokenAndCode(token, code);

        loginPageWithVerificationView.addObject("success", "Аккаунт успешно подтвержден, теперь в него можно войти");
        return loginPageWithVerificationView;
    }

    @Override
    public ModelAndView getRegisterWithSuccessView(User user, BindingResult result, String firstName, String lastName, String email, String password, String confirmPassword) {
        ModelAndView registrationPage = new ModelAndView("register");

        //TODO сделать проверку занятости почты

        if(result.hasErrors() && confirmPassword.isEmpty()) {
            registrationPage.addObject("confirm_pass", "Подтвердите введённый пароль");
            return registrationPage;
        }

        if (!password.equals(confirmPassword)) {
            registrationPage.addObject("passwordMisMatch", "Введённые пароли не совпадают");
            return registrationPage;
        }

        String token = Token.generate();

        Random random = new Random();
        int bound = 123;
        int code = bound * random.nextInt(bound);

        String emailBody = VerificationEmail.generateView(token, Integer.toString(code));

        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        User userToRegister = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(hashed_password)
                .token(token)
                .code(Integer.toString(code))
                .build();
        userRepository.save(userToRegister);

        try {
            MailBot.sendEmailMessage("a12344321123@mail.ru", email, "Подтверждение аккаунта", emailBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        String successMessage = "Аккаунт успешно зарегистрирован, проверьте вашу почту и подтвердите его";
        registrationPage.addObject("success", successMessage);

        return registrationPage;
    }

}
