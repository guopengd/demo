package com.example.demo.token;

public class apiKey {
    static String secret = "guopengdong";

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String apiSecret) {
        secret = apiSecret;
    }
}
