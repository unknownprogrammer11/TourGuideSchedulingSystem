package services;

import java.util.ArrayList;
import models.Admin;

public class AdminService {
    private ArrayList<Admin> admins = new ArrayList<>();

    public void addAdmin(Admin admin) { admins.add(admin); }

    public Admin login(String un, String pw) {
        for (Admin a : admins)
            if (a.getUsername().equals(un) && a.getPassword().equals(pw))
                return a;
        return null;
    }
}
