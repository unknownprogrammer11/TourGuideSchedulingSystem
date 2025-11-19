package models;

public class Tourist extends Person {
    private String nationality;

    public Tourist(String fn, String ln, String ad, String nat) {
        super(fn, ln, ad);
        this.nationality = nat;
    }

    public String getNationality() { return nationality; }
    public void setNationality(String n) { this.nationality = n; }
}
