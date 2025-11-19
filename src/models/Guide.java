package models;

public class Guide extends Person {
    private String languages;

    public Guide(String fn, String ln, String ad, String lang) {
        super(fn, ln, ad);
        this.languages = lang;
    }

    public String getLanguages() { return languages; }
    public void setLanguages(String l) { this.languages = l; }
}
