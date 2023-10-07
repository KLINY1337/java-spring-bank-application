package com.example.demobank.util;

import java.security.SecureRandom;

public class AccountNumber {

    public static String generate() {
        SecureRandom secureRandom = new SecureRandom();
        long lowerBound = 1000_0000_0000_0000L;
        long upperBound = 9999_9999_9999_9999L;

        long randomNumber = secureRandom.nextLong() % (upperBound - lowerBound + 1) + lowerBound;

        return Long.toString(randomNumber);
    }
}
