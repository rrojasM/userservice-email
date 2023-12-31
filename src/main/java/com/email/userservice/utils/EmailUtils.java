package com.email.userservice.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token) {

        return "Hello, " + name + "\n\nYour new account has been created. Please click in the link to verify your account. \n\n " +
                getVerificationUrl(host, token) + "\n\nThe support team.";
    }

    public static String getVerificationUrl(String host, String token) {
        return host + "/api/users?token=" + token;
    }

}
