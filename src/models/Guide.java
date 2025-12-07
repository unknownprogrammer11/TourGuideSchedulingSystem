package models;

public class Guide extends Person {
    private String languages;

    public Guide(String fn, String ln, String ad, String lang) {
        super(fn, ln, ad);
        this.languages = lang;
    }

    public String getLanguages() { return languages; }
    public void setLanguages(String l) { this.languages = l; }
    
    // Method to get detailed information
    public String getDetailedInfo() {
        return String.format(
            "Guide: %s %s\n" +
            "  Languages: %s\n" +
            "  Address: %s",
            getFirstName(), getLastName(),
            languages,
            getAddress()
        );
    }
    
    // Method to get brief information
    public String getBriefInfo() {
        return String.format("%s %s | Languages: %s", 
            getFirstName(), getLastName(), languages);
    }
    
    @Override
    public String displayInfo() {
        return "Guide: " + getFullName() + " | Languages: " + languages;
    }
}