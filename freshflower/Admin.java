package freshflower;

public class Admin {
    private String username="admin";
    private String password="admin123";

    //setters and getters
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //method to check login credentials
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

}
