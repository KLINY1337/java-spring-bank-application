package com.example.demobank.helpers;

public class HTML {

    public static String htmlEmailTemplate(String token, String code) {

        String url = "http://127.0.0.1:8070/verify?token=" + token + "&code=" + code;

        return "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "  <head>\n" +
                "    <meta charset='UTF-8' />\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />\n" +
                "    <!-- <link rel='stylesheet' href='css/email.css'> -->\n" +
                "    <title>Email</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "          box-sizing: border-box;\n" +
                "          font-family: Arial, Helvetica, sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "          height: 100vh;\n" +
                "          background-color: rgb(200, 200, 200);\n" +
                "          display: flex;\n" +
                "          align-items: center;\n" +
                "          justify-content: center;\n" +
                "        }\n" +
                "\n" +
                "        /*<!--Wrapper--> */\n" +
                "        .wrapper {\n" +
                "          width: 550px;\n" +
                "          height: auto;\n" +
                "          padding: 15px;\n" +
                "          background-color: white;\n" +
                "          border-radius: 7px;\n" +
                "        }\n" +
                "\n" +
                "        .email-msg-header {\n" +
                "          text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .company-name {\n" +
                "          text-align: center;\n" +
                "          width: 100%;\n" +
                "          margin: 15px 0px;\n" +
                "          font-size: 3vh;\n" +
                "          color: gray;\n" +
                "        }\n" +
                "\n" +
                "        .welcome-text {\n" +
                "          text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .verify-account-btn {\n" +
                "          padding: 15px;\n" +
                "          background-color: black;\n" +
                "          text-decoration: none;\n" +
                "          color: white;\n" +
                "          border-radius: 5px;\n" +
                "        }\n" +
                "\n" +
                "        .copy-right{\n" +
                "          text-align: center;\n" +
                "          padding: 15px;\n" +
                "          color: gray;\n" +
                "          font-size: 2vh;\n" +
                "          margin: 20px 0px;\n" +
                "          display: flex;\n" +
                "          align-items: center;\n" +
                "          justify-content: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "<!--Wrapper-->\n" +
                "    <div class='wrapper'>\n" +
                "          <!--Email msg header-->\n" +
                "          <h2 class='email-msg-header'>\n" +
                "            Welcome and Thank you for choosing <br>\n" +
                "            \n" +
                "             \n" +
                "          </h2>\n" +
                "          <div class='company-name'>Chernomuroff Bank </div>\n" +
                "          <hr>\n" +
                "\n" +
                "          <p class='welcome-text'>\n" +
                "            Your account has been successfully registered, please click below to verify your account\n" +
                "          </p>\n" +
                "\n" +
                "          <br><br>\n" +
                "\n" +
                "          <!--Verify button-->\n" +
                "          <center><a href='" + url + "' class='verify-account-btn' role='button'>Verify account</a></center>\n" +
                "\n" +
                "          <div class='copy-right'>\n" +
                "            &copy; Copy right 2023. All right reserved\n" +
                "          </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html> \n";
    }
}
