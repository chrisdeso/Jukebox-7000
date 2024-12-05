// Account.java
public class Account {
    private String username;
    private String password;
    private boolean isLoggedIn;
    
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }
    
    public boolean login(String password) {
        if (this.password.equals(password)) {
            isLoggedIn = true;
            System.out.println("Login successful for user: " + username);
            return true;
        } else {
            System.out.println("Login failed: incorrect password");
            return false;
        }
    }
    
    public void logout() {
        isLoggedIn = false;
        System.out.println("User logged out: " + username);
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public String getUsername() {
        return username;
    }
}