package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Tourist extends Person {
    private String nationality;
    private String birthday; // Format: "yyyy-MM-dd"
    
    public Tourist(String fn, String ln, String ad, String nat, String birthday) {
        super(fn, ln, ad);
        this.nationality = nat;
        this.birthday = birthday;
    }
    
    public String getNationality() { return nationality; }
    public void setNationality(String n) { this.nationality = n; }
    
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    
    // Method to calculate age from birthday
    public int getAge() {
        if (birthday == null || birthday.trim().isEmpty()) {
            return -1; // Return -1 if no birthday set
        }
        
        try {
            // Parse the birthday string to LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthday, formatter);
            LocalDate currentDate = LocalDate.now();
            
            // Calculate period between birthday and current date
            return Period.between(birthDate, currentDate).getYears();
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format in Tourist ID: " + getFullName());
            return -1;
        }
    }
    
 @Override
    public String displayInfo() {
        int age = getAge();
        String ageDisplay = (age >= 0) ? String.valueOf(age) : "Not set";
        return "Tourist: " + getFullName() + 
               " | Nationality: " + nationality + 
               " | Birthday: " + (birthday == null || birthday.isEmpty() ? "Not set" : birthday) + 
               " | Age: " + ageDisplay;
    }
}