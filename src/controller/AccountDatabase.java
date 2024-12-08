package controller;

import model.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountDatabase {
    private Map<String, Account> accounts;
    private Account currentUser;

    public AccountDatabase() {
        accounts = new HashMap<>();
        // Pre-populate with three users
        accounts.put("Khanh", new Account("Khanh", "password1"));
        accounts.put("Chris", new Account("Chris", "password2"));
        accounts.put("Hilario", new Account("Hilario", "password3"));
        // Add admin user
        accounts.put("Admin", new Account("Admin", "")); // No password for admin
    }

    public Account getAccount(String username) {
        return accounts.get(username);
    }

    public boolean validateUser(String username, String password) {
        if (username.equals("Admin")) {
            return true; // Admin can log in without a password
        }
        Account account = accounts.get(username);
        return account != null && account.getPassword().equals(password);
    }

    public void setCurrentUser(Account user) {
        this.currentUser = user;
    }

    public Account getCurrentUser() {
        return currentUser;
    }
}
