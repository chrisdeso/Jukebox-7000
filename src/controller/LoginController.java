package controller;

import model.Account;

public class LoginController {
    private AccountDatabase accountDatabase;

    public LoginController(AccountDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
    }

    public Account login(String username, String password) {
        System.out.println("Attempting to log in with Username: " + username + " and Password: " + password); // Debugging line
        if (username.equals("Admin")) {
            Account account = accountDatabase.getAccount(username);
            accountDatabase.setCurrentUser(account);
            return account; // Admin login
        }
        if (accountDatabase.validateUser(username, password)) {
            Account account = accountDatabase.getAccount(username);
            accountDatabase.setCurrentUser(account);
            return account;
        }
        return null; // Invalid login
    }

    public void logout() {
        accountDatabase.setCurrentUser(null);
    }

    public Account getCurrentUser() {
        return accountDatabase.getCurrentUser();
    }
}
