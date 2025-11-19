package models;

import java.util.ArrayList;

public class Schedule {
    private String tourName;
    private String dateTime;
    private Guide guide;
    private ArrayList<Tourist> tourists;

    public Schedule(String tn, String dt, Guide g, ArrayList<Tourist> t) {
        this.tourName = tn;
        this.dateTime = dt;
        this.guide = g;
        this.tourists = t;
    }

    public void displayScheduleDetails() {
        System.out.println("\n" + tourName + " | " + dateTime);
        System.out.println("Guide: " + guide.getFullName());
        System.out.println("Tourists:");
        for (Tourist tr : tourists)
            System.out.println(" - " + tr.getFullName());
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

}
