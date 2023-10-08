package com.example.demobank.util;

import java.security.SecureRandom;
import java.util.UUID;

public class SecurityUtils {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static String generateCode() {
        SecureRandom random = new SecureRandom();

        return String.valueOf(random.nextInt(1000, 9999));
    }
}
