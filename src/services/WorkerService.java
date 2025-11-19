package services;

import java.util.ArrayList;
import models.Worker;

public class WorkerService {
    private ArrayList<Worker> workers = new ArrayList<>();

    public void addWorker(Worker worker) { workers.add(worker); }

    public Worker login(String un, String pw) {
        for (Worker w : workers)
            if (w.getUsername().equals(un) && w.getPassword().equals(pw))
                return w;
        return null;
    }

    public ArrayList<Worker> getAllWorkers() { return workers; }
}
