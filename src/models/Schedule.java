package models;

import java.util.ArrayList;

public class Schedule {
    private String tourName;
    private String dateTime;
    private ArrayList<Guide> guides;  // Changed from single Guide to ArrayList
    private ArrayList<Tourist> tourists;

    public Schedule(String tn, String dt, ArrayList<Guide> guides, ArrayList<Tourist> tourists) {
        this.tourName = tn;
        this.dateTime = dt;
        this.guides = guides;
        this.tourists = tourists;
    }
    
    // Constructor with single guide (for backward compatibility)
    public Schedule(String tn, String dt, Guide guide, ArrayList<Tourist> tourists) {
        this.tourName = tn;
        this.dateTime = dt;
        this.guides = new ArrayList<>();
        this.guides.add(guide);
        this.tourists = tourists;
    }

    public void displayScheduleDetails() {
        System.out.println("\n" + tourName + " | " + dateTime);
        System.out.println("Guides (" + guides.size() + "):");
        for (Guide g : guides) {
            System.out.println(" - " + g.getFullName() + " (" + g.getLanguages() + ")");
        }
        System.out.println("Tourists (" + tourists.size() + "):");
        for (Tourist tr : tourists) {
            System.out.println(" - " + tr.getFullName() + " (" + tr.getNationality() + ")");
        }
        System.out.println("Total participants: " + (guides.size() + tourists.size()));
    }
    
    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public ArrayList<Guide> getGuides() {
        return guides;
    }
    
    public void setGuides(ArrayList<Guide> guides) {
        this.guides = guides;
    }
    
    public ArrayList<Tourist> getTourists() {
        return tourists;
    }
    
    public void setTourists(ArrayList<Tourist> tourists) {
        this.tourists = tourists;
    }
    
    // Method to add a guide to the schedule
    public boolean addGuide(Guide guide) {
        if (!guides.contains(guide)) {
            guides.add(guide);
            return true;
        }
        return false; // Guide already in schedule
    }
    
    // Method to add a tourist to the schedule
    public boolean addTourist(Tourist tourist) {
        if (!tourists.contains(tourist)) {
            tourists.add(tourist);
            return true;
        }
        return false; // Tourist already in schedule
    }
    
    // Method to remove a guide
    public boolean removeGuide(Guide guide) {
        return guides.remove(guide);
    }
    
    // Method to remove a tourist
    public boolean removeTourist(Tourist tourist) {
        return tourists.remove(tourist);
    }
}