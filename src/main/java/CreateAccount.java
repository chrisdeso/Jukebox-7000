// CreateAccount.java

import java.util.ArrayList;

public class CreateAccount {
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static ArrayList<String> existingUsernames = new ArrayList<>();
    
    public static Account createAccount(String username, String password) {
        if (!validateUsername(username)) {
            System.out.println("Invalid username");
            return null;
        }
        
        if (!validatePassword(password)) {
            System.out.println("Invalid password");
            return null;
        }
        
        if (isUsernameTaken(username)) {
            System.out.println("Username already taken");
            return null;
        }
        
        Account newAccount = new Account(username, password);
        existingUsernames.add(username);
        System.out.println("Account created successfully");
        return newAccount;
    }
    
    private static boolean validateUsername(String username) {
        // Basic validation: username should be at least 3 characters and alphanumeric
        return username != null && username.length() >= 3 && username.matches("^[a-zA-Z0-9]+$");
    }
    
    private static boolean validatePassword(String password) {
        // Basic validation: password should be at least MIN_PASSWORD_LENGTH characters
        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
    }
    
    private static boolean isUsernameTaken(String username) {
        return existingUsernames.contains(username);
    }
}