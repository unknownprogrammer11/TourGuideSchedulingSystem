package services;

import java.util.ArrayList;
import models.Schedule;

public class ScheduleService {
    private ArrayList<Schedule> schedules = new ArrayList<>();

    public void addSchedule(Schedule s) { schedules.add(s); }
    public ArrayList<Schedule> getAllSchedules() { return schedules; }
}
