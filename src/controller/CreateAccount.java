package controller;

import model.Account;

public class CreateAccount {
    private static final int MIN_PASSWORD_LENGTH = 6;

    public Account createAccount(String username, String password) {
        if (!validateUsername(username)) {
            throw new IllegalArgumentException("Invalid username.");
        }
        if (!validatePassword(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }
        // Logic to check if username is taken
        return new Account(username, password);
    }

    private boolean validateUsername(String username) {
        // Add logic to validate username (e.g., non-empty, unique)
        return !username.isEmpty();
    }

    private boolean validatePassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }
}
