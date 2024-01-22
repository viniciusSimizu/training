package com.linkedrh.training.lib.helpers;

public class VerifyAuthorization {

    public static boolean verifyToken(String token) {
        final String correctToken = "Bearer token_exercicio_rest";

        return correctToken.equals(token);
    }
}
