/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.*;
import models.*;
import services.*;

public class MainMenu {
    private Scanner sc = new Scanner(System.in);
    private AdminService adminService = new AdminService();
    private WorkerService workerService = new WorkerService();
    private TouristService touristService = new TouristService();
    private GuideService guideService = new GuideService();
    private ScheduleService scheduleService = new ScheduleService();

    public void start() {
        // Default Admin
        adminService.addAdmin(new Admin("System", "Admin", "Main Office", "admin", "admin123"));

        int choice;
        do {
            System.out.println("\n=====================================");
            System.out.println("   TOUR GUIDE SCHEDULING SYSTEM");
            System.out.println("=====================================");
            System.out.println("[1] Admin Login");
            System.out.println("[2] Worker Login");
            System.out.println("[0] Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> workerLogin();
                case 0 -> System.out.println("Exiting System...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    // --- Admin Section ---
    private void adminLogin() {
        sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Admin admin = adminService.login(username, password);
        if (admin != null) adminMenu(admin);
        else System.out.println("Invalid username or password.");
    }

    private void adminMenu(Admin admin) {
        int choice;
        do {
            System.out.println("\n=====================================");
            System.out.println("   TOUR GUIDE SCHEDULING SYSTEM");
            System.out.println("          (Admin Access)");
            System.out.println("=====================================");
            System.out.println("[1] Create Worker Account");
            System.out.println("[2] View All Workers");
            System.out.println("[3] View All Tourists");
            System.out.println("[4] View All Guides");
            System.out.println("[5] View All Schedules");
            System.out.println("[6] Edit Tourist Information");
            System.out.println("[7] Edit Guide Information");
            System.out.println("[8] Edit Worker Information");
            System.out.println("[9] Edit Schedule");
            System.out.println("[0] Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createWorker();
                case 2 -> viewWorkers();
                case 3 -> viewTourists();
                case 4 -> viewGuides();
                case 5 -> viewSchedules();
                case 6 -> editTourist();
                case 7 -> editGuide();
                case 8 -> editWorker();
                case 9 -> editSchedule();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private void createWorker() {
        sc.nextLine();
        System.out.print("Firstname: ");
        String fn = sc.nextLine();
        System.out.print("Lastname: ");
        String ln = sc.nextLine();
        System.out.print("Address: ");
        String ad = sc.nextLine();
        System.out.print("Username: ");
        String un = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();
        workerService.addWorker(new Worker(fn, ln, ad, un, pw));
        System.out.println("Worker account created successfully!");
    }

    private void viewWorkers() {
        System.out.println("----- WORKER RECORDS -----");
        for (Worker w : workerService.getAllWorkers()) {
            System.out.println(w.getFullName() + " (" + w.getUsername() + ")");
        }
    }

    private void viewTourists() {
        System.out.println("----- TOURIST RECORDS -----");
        for (Tourist t : touristService.getAllTourists()) {
            System.out.println(t.getFullName() + " - " + t.getNationality());
        }
    }

    private void viewGuides() {
        System.out.println("----- GUIDE RECORDS -----");
        for (Guide g : guideService.getAllGuides()) {
            System.out.println(g.getFullName() + " - " + g.getLanguages());
        }
    }

    private void viewSchedules() {
        System.out.println("----- TOUR SCHEDULES -----");
        for (Schedule s : scheduleService.getAllSchedules()) {
            s.displayScheduleDetails();
        }
    }

    // --- Worker Section ---
    private void workerLogin() {
        sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Worker worker = workerService.login(username, password);
        if (worker != null) workerMenu(worker);
        else System.out.println("Invalid worker credentials.");
    }

    private void workerMenu(Worker worker) {
        int choice;
        do {
            System.out.println("\n=====================================");
            System.out.println("   TOUR GUIDE SCHEDULING SYSTEM");
            System.out.println("          (Worker Access)");
            System.out.println("=====================================");
            System.out.println("[1] Register Tourist");
            System.out.println("[2] Register Guide");
            System.out.println("[3] Create Schedule");
            System.out.println("[0] Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> registerTourist();
                case 2 -> registerGuide();
                case 3 -> createSchedule();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private void registerTourist() {
        sc.nextLine();
        System.out.print("Firstname: ");
        String fn = sc.nextLine();
        System.out.print("Lastname: ");
        String ln = sc.nextLine();
        System.out.print("Address: ");
        String ad = sc.nextLine();
        System.out.print("Nationality: ");
        String nat = sc.nextLine();
        touristService.addTourist(new Tourist(fn, ln, ad, nat));
        System.out.println("Tourist registered successfully!");
    }

    private void registerGuide() {
        sc.nextLine();
        System.out.print("Firstname: ");
        String fn = sc.nextLine();
        System.out.print("Lastname: ");
        String ln = sc.nextLine();
        System.out.print("Address: ");
        String ad = sc.nextLine();
        System.out.print("Languages: ");
        String lang = sc.nextLine();
        guideService.addGuide(new Guide(fn, ln, ad, lang));
        System.out.println("Guide registered successfully!");
    }

    private void createSchedule() {
        sc.nextLine();
        System.out.print("Tour Name: ");
        String tourName = sc.nextLine();
        System.out.print("Date/Time: ");
        String dateTime = sc.nextLine();

        if (guideService.getAllGuides().isEmpty() || touristService.getAllTourists().isEmpty()) {
            System.out.println("Error: You must have at least one guide and one tourist.");
            return;
        }

        // Select Guide (1-based)
        System.out.println("\nSelect Guide:");
        for (int i = 0; i < guideService.getAllGuides().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + guideService.getAllGuides().get(i).getFullName());
        }
        System.out.print("Enter number of guide: ");
        int guideChoice = sc.nextInt();

        if (guideChoice < 1 || guideChoice > guideService.getAllGuides().size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Guide guide = guideService.getAllGuides().get(guideChoice - 1);

        // Select Tourists (1-based)
        ArrayList<Tourist> selectedTourists = new ArrayList<>();
        System.out.println("\nSelect Tourists (Enter 0 to finish):");
        for (int i = 0; i < touristService.getAllTourists().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + touristService.getAllTourists().get(i).getFullName());
        }

        int choice;
        do {
            System.out.print("Choose tourist number (0 to stop): ");
            choice = sc.nextInt();

            if (choice > 0 && choice <= touristService.getAllTourists().size()) {
                Tourist selected = touristService.getAllTourists().get(choice - 1);
                if (!selectedTourists.contains(selected)) {
                    selectedTourists.add(selected);
                    System.out.println("Added: " + selected.getFullName());
                } else {
                    System.out.println("Tourist already selected!");
                }
            } else if (choice != 0) {
                System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        if (selectedTourists.isEmpty()) {
            System.out.println("No tourists selected. Schedule not created.");
            return;
        }

        scheduleService.addSchedule(new Schedule(tourName, dateTime, guide, selectedTourists));
        System.out.println("Schedule created successfully!");
    }

    // ---- ADDED EDIT FEATURES (ADMIN ONLY) ----
    private void editTourist() {
        if (touristService.getAllTourists().isEmpty()) {
            System.out.println("No tourists available to edit.");
            return;
        }

        System.out.println("----- EDIT TOURIST INFORMATION -----");
        for (int i = 0; i < touristService.getAllTourists().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + touristService.getAllTourists().get(i).getFullName());
        }

        System.out.print("Select Tourist number to edit: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 1 || index > touristService.getAllTourists().size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Tourist t = touristService.getAllTourists().get(index - 1);

        System.out.print("New Firstname (" + t.getFirstName() + "): ");
        t.setFirstName(sc.nextLine());
        System.out.print("New Lastname (" + t.getLastName() + "): ");
        t.setLastName(sc.nextLine());
        System.out.print("New Address (" + t.getAddress() + "): ");
        t.setAddress(sc.nextLine());
        System.out.print("New Nationality (" + t.getNationality() + "): ");
        t.setNationality(sc.nextLine());

        System.out.println("Tourist information updated successfully!");
    }

    private void editGuide() {
        if (guideService.getAllGuides().isEmpty()) {
            System.out.println("No guides available to edit.");
            return;
        }

        System.out.println("----- EDIT GUIDE INFORMATION -----");
        for (int i = 0; i < guideService.getAllGuides().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + guideService.getAllGuides().get(i).getFullName());
        }

        System.out.print("Select Guide number to edit: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 1 || index > guideService.getAllGuides().size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Guide g = guideService.getAllGuides().get(index - 1);

        System.out.print("New Firstname (" + g.getFirstName() + "): ");
        g.setFirstName(sc.nextLine());
        System.out.print("New Lastname (" + g.getLastName() + "): ");
        g.setLastName(sc.nextLine());
        System.out.print("New Address (" + g.getAddress() + "): ");
        g.setAddress(sc.nextLine());
        System.out.print("New Languages (" + g.getLanguages() + "): ");
        g.setLanguages(sc.nextLine());

        System.out.println("Guide information updated successfully!");
    }

    private void editWorker() {
        if (workerService.getAllWorkers().isEmpty()) {
            System.out.println("No workers available to edit.");
            return;
        }

        System.out.println("----- EDIT WORKER INFORMATION -----");
        for (int i = 0; i < workerService.getAllWorkers().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + workerService.getAllWorkers().get(i).getFullName());
        }

        System.out.print("Select Worker number to edit: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 1 || index > workerService.getAllWorkers().size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Worker w = workerService.getAllWorkers().get(index - 1);

        System.out.print("New Firstname (" + w.getFirstName() + "): ");
        String newFirst = sc.nextLine();
        System.out.print("New Lastname (" + w.getLastName() + "): ");
        String newLast = sc.nextLine();
        System.out.print("New Address (" + w.getAddress() + "): ");
        String newAddr = sc.nextLine();
        System.out.print("New Username (" + w.getUsername() + "): ");
        String newUser = sc.nextLine();
        System.out.print("New Password: ");
        String newPass = sc.nextLine();

        workerService.getAllWorkers().set(index - 1, new Worker(newFirst, newLast, newAddr, newUser, newPass));
        System.out.println("Worker information updated successfully!");
    }

    private void editSchedule() {
        if (scheduleService.getAllSchedules().isEmpty()) {
            System.out.println("No schedules available to edit.");
            return;
        }
                System.out.println("----- EDIT SCHEDULE -----");
        for (int i = 0; i < scheduleService.getAllSchedules().size(); i++) {
            Schedule s = scheduleService.getAllSchedules().get(i);
            System.out.println("[" + (i + 1) + "] " + s.getTourName() + " (" + s.getDateTime() + ")");
        }

        System.out.print("Select Schedule number to edit: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 1 || index > scheduleService.getAllSchedules().size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Schedule s = scheduleService.getAllSchedules().get(index - 1);

        System.out.print("New Tour Name (" + s.getTourName() + "): ");
        s.setTourName(sc.nextLine());
        System.out.print("New Date/Time (" + s.getDateTime() + "): ");
        s.setDateTime(sc.nextLine());

        System.out.println("Schedule updated successfully!");

        System.out.println("Schedule updated successfully!");
    }
}
