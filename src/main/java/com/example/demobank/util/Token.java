package com.example.demobank.util;

import java.util.UUID;

public class Token {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
