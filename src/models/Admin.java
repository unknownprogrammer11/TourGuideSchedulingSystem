package models;

public class Admin extends Person {
    private String username;
    private String password;

    public Admin(String fn, String ln, String ad, String un, String pw) {
        super(fn, ln, ad);
        this.username = un;
        this.password = pw;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
