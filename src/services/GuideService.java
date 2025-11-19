package services;

import java.util.ArrayList;
import models.Guide;

public class GuideService {
    private ArrayList<Guide> guides = new ArrayList<>();

    public void addGuide(Guide g) { guides.add(g); }
    public ArrayList<Guide> getAllGuides() { return guides; }
}
