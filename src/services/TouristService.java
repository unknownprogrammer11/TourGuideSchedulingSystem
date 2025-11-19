package services;

import java.util.ArrayList;
import models.Tourist;

public class TouristService {
    private ArrayList<Tourist> tourists = new ArrayList<>();

    public void addTourist(Tourist t) { tourists.add(t); }
    public ArrayList<Tourist> getAllTourists() { return tourists; }
}
