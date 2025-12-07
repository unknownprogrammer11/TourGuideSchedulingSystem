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
            System.out.println("   TOURIST SCHEDULING SYSTEM");
            System.out.println("=====================================");
            System.out.println("[1] Admin Login");
            System.out.println("[2] Worker Login");
            System.out.println("[0] Exit");
            choice = getIntInput("Enter choice: ");

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
            System.out.println("   TOURIST SCHEDULING SYSTEM");
            System.out.println("          (Admin Access)");
            System.out.println("=====================================");
            System.out.println("[1] Create Worker Account");
            System.out.println("[2] View All Workers");
            System.out.println("[3] View All Tourists");
            System.out.println("[4] View All Guides");
            System.out.println("[5] View All Schedules");
            System.out.println("[0] Logout");
            choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1 -> createWorker();
                case 2 -> viewWorkers();
                case 3 -> viewTourists();
                case 4 -> viewGuides();
                case 5 -> viewSchedules();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private void createWorker() {
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
        System.out.println(w.displayInfo());  // Changed this line
    }
}
    private void viewTourists() {
    System.out.println("----- TOURIST RECORDS -----");
    for (Tourist t : touristService.getAllTourists()) {
        System.out.println(t.displayInfo());  // Changed this line
    }
}

    private void viewGuides() {
    System.out.println("----- GUIDE RECORDS -----");
    for (Guide g : guideService.getAllGuides()) {
        System.out.println(g.displayInfo());  // Changed this line
    }
}

  private void viewSchedules() {
    System.out.println("----- TOUR SCHEDULES -----");
    for (Schedule s : scheduleService.getAllSchedules()) {
        s.displayScheduleDetails();  // Keep this as is
    }
}

  private void workerLogin() {
    System.out.print("Username: ");
    String username = sc.nextLine();
    System.out.print("Password: ");
    String password = sc.nextLine();

    Worker worker = workerService.login(username, password);
    if (worker != null) {
        System.out.println("\nWelcome, " + worker.getFullName() + " | Username: " + username + "!");
        workerMenu(worker);
    }
    else System.out.println("Invalid worker credentials.");
}

    private void workerMenu(Worker worker) {
        int choice;
        do {
            System.out.println("\n=====================================");
            System.out.println("   TOURIST SCHEDULING SYSTEM");
            System.out.println("          (Worker Access)");
            System.out.println("=====================================");
            System.out.println("[1] Register Tourist");
            System.out.println("[2] Register Guide");
            System.out.println("[3] Create Schedule");
            System.out.println("[4] Add to Existing Schedule");
            System.out.println("[5] Edit Tourist Information");
            System.out.println("[6] Edit Guide Information");
            System.out.println("[7] Edit Worker Information");
            System.out.println("[8] Edit Schedule");
            System.out.println("[9] Delete Tourist");
            System.out.println("[10] Delete Guide");
            System.out.println("[11] Delete Worker");
            System.out.println("[12] Delete Schedule");
            System.out.println("[0] Logout");
            choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1 -> registerTourist();
                case 2 -> registerGuide();
                case 3 -> createSchedule();
                case 4 -> selectAndAddToSchedule();
                case 5 -> editTourist();
                case 6 -> editGuide();
                case 7 -> editWorker();
                case 8 -> editSchedule();
                case 9 -> deleteTourist();
                case 10 -> deleteGuide();
                case 11 -> deleteWorker();
                case 12 -> deleteSchedule();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    // --- Tourist Registration ---
    private void registerTourist() {
        System.out.print("Firstname: ");
        String fn = sc.nextLine();
        System.out.print("Lastname: ");
        String ln = sc.nextLine();
        System.out.print("Address: ");
        String ad = sc.nextLine();
        System.out.print("Nationality: ");
        String nat = sc.nextLine();
        // Get and validate birthday
        String birthday = "";
        boolean validDate = false;
        
        while (!validDate) {
            System.out.print("Birthday (yyyy-MM-dd): ");
            birthday = sc.nextLine();
            
            // Validate date format
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                java.time.LocalDate.parse(birthday, formatter);
                validDate = true;
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd (e.g., 2005-08-15).");
            }
        }
        
        touristService.addTourist(new Tourist(fn, ln, ad, nat, birthday));
        System.out.println("Tourist registered successfully!");
    }

    private void registerGuide() {
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

    // --- Schedule Management Methods (must be in correct order) ---
    
    // First: Helper methods for adding to schedules
    private void addGuidesToSchedule(Schedule schedule) {
        if (guideService.getAllGuides().isEmpty()) {
            System.out.println("No guides available to add.");
            return;
        }
        
        System.out.println("\nAvailable Guides:");
        for (int i = 0; i < guideService.getAllGuides().size(); i++) {
            Guide g = guideService.getAllGuides().get(i);
            System.out.println("[" + (i + 1) + "] " + g.displayInfo() + 
                  " (Already in schedule: " + schedule.getGuides().contains(g) + ")");
        }
        
        int guideChoice;
        do {
            guideChoice = getIntInput("Choose guide to add (0 to stop): ");
            if (guideChoice > 0 && guideChoice <= guideService.getAllGuides().size()) {
                Guide selected = guideService.getAllGuides().get(guideChoice - 1);
                if (schedule.addGuide(selected)) {
                    System.out.println("Added Guide: " + selected.getFullName());
                } else {
                    System.out.println("Guide is already in the schedule!");
                }
            } else if (guideChoice != 0) {
                System.out.println("---Invalid choice! input the existing number.----");
            }
        } while (guideChoice != 0);
    }

    private void addTouristsToSchedule(Schedule schedule) {
        if (touristService.getAllTourists().isEmpty()) {
            System.out.println("No tourists available to add.");
            return;
        }
        
        System.out.println("\nAvailable Tourists:");
        for (int i = 0; i < touristService.getAllTourists().size(); i++) {
            Tourist t = touristService.getAllTourists().get(i);
           System.out.println("[" + (i + 1) + "] " + t.displayInfo() + 
                  " (Already in schedule: " + schedule.getTourists().contains(t) + ")");
        }
        
        int touristChoice;
        do {
            touristChoice = getIntInput("Choose tourist to add (0 to stop): ");
            if (touristChoice > 0 && touristChoice <= touristService.getAllTourists().size()) {
                Tourist selected = touristService.getAllTourists().get(touristChoice - 1);
                if (schedule.addTourist(selected)) {
                    System.out.println("Added Tourist: " + selected.getFullName());
                } else {
                    System.out.println("Tourist is already in the schedule!");
                }
            } else if (touristChoice != 0) {
                System.out.println("---Invalid choice! input the existing number.----");
            }
        } while (touristChoice != 0);
    }

    private void addToExistingSchedule(Schedule existingSchedule) {
        System.out.println("\n===== ADD TO EXISTING SCHEDULE =====");
        System.out.println("Schedule: " + existingSchedule.getTourName() + " on " + existingSchedule.getDateTime());
        
        int choice;
        do {
            System.out.println("\n[1] Add Guides");
            System.out.println("[2] Add Tourists");
            System.out.println("[3] View Current Schedule");
            System.out.println("[0] Done");
            choice = getIntInput("Enter choice: ");
            
            switch (choice) {
                case 1 -> addGuidesToSchedule(existingSchedule);
                case 2 -> addTouristsToSchedule(existingSchedule);
                case 3 -> existingSchedule.displayScheduleDetails();
                case 0 -> System.out.println("Finished updating schedule.");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    // Second: Method to select which schedule to modify
    private void selectAndAddToSchedule() {
        if (scheduleService.getAllSchedules().isEmpty()) {
            System.out.println("No schedules available.");
            return;
        }
        
        System.out.println("\n===== SELECT SCHEDULE TO MODIFY =====");
        for (int i = 0; i < scheduleService.getAllSchedules().size(); i++) {
            Schedule s = scheduleService.getAllSchedules().get(i);
            System.out.println("[" + (i + 1) + "] " + s.getTourName() + 
                              " (" + s.getDateTime() + ")");
        }
        
        int choice = getIntInput("Select schedule (0 to cancel): ");
        if (choice == 0) {
            System.out.println("Cancelled.");
            return;
        }
        
        if (choice < 1 || choice > scheduleService.getAllSchedules().size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        Schedule selected = scheduleService.getAllSchedules().get(choice - 1);
        addToExistingSchedule(selected);
    }

    // Third: Create new schedule (uses addToExistingSchedule)
    private void createSchedule() {
        System.out.print("Tour Name: ");
        String tourName = sc.nextLine();
        System.out.print("Date/Time: ");
        String dateTime = sc.nextLine();

        if (guideService.getAllGuides().isEmpty() || touristService.getAllTourists().isEmpty()) {
            System.out.println("Error: You must have at least one guide and one tourist.");
            return;
        }

        // Check if schedule already exists
        for (Schedule existing : scheduleService.getAllSchedules()) {
            if (existing.getTourName().equalsIgnoreCase(tourName) && 
                existing.getDateTime().equals(dateTime)) {
                System.out.println("A schedule with this name and date/time already exists!");
                System.out.println("Do you want to add participants to the existing schedule? (y/n)");
                String choice = sc.nextLine().toLowerCase();
                if (choice.equals("y") || choice.equals("yes")) {
                    addToExistingSchedule(existing);
                    return;
                } else {
                    System.out.println("Please use a different tour name or date/time.");
                    return;
                }
            }
        }

        // Select Multiple Guides
        ArrayList<Guide> selectedGuides = new ArrayList<>();
        System.out.println("\nSelect Guides (Enter 0 to finish):");
        for (int i = 0; i < guideService.getAllGuides().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + guideService.getAllGuides().get(i).displayInfo());
        }

        int guideChoice;
        do {
            guideChoice = getIntInput("Choose guide number (0 to stop): ");
            if (guideChoice > 0 && guideChoice <= guideService.getAllGuides().size()) {
                Guide selected = guideService.getAllGuides().get(guideChoice - 1);
                if (!selectedGuides.contains(selected)) {
                    selectedGuides.add(selected);
                    System.out.println("Added Guide: " + selected.getFullName());
                } else {
                    System.out.println("Guide already selected!");
                }
            } else if (guideChoice != 0) {
                System.out.println("---Invalid choice! input the existing number.----");
            }
        } while (guideChoice != 0);

        if (selectedGuides.isEmpty()) {
            System.out.println("At least one guide is required!");
            return;
        }

        // Select Tourists
        ArrayList<Tourist> selectedTourists = new ArrayList<>();
        System.out.println("\nSelect Tourists (Enter 0 to finish):");
        for (int i = 0; i < touristService.getAllTourists().size(); i++) {
           System.out.println("[" + (i + 1) + "] " + touristService.getAllTourists().get(i).displayInfo());
        }

        int touristChoice;
        do {
            touristChoice = getIntInput("Choose tourist number (0 to stop): ");
            if (touristChoice > 0 && touristChoice <= touristService.getAllTourists().size()) {
                Tourist selected = touristService.getAllTourists().get(touristChoice - 1);
                if (!selectedTourists.contains(selected)) {
                    selectedTourists.add(selected);
                    System.out.println("Added Tourist: " + selected.getFullName());
                } else {
                    System.out.println("Tourist already selected!");
                }
            } else if (touristChoice != 0) {
                System.out.println("---Invalid choice! input the existing number.----");
            }
        } while (touristChoice != 0);

        if (selectedTourists.isEmpty()) {
            System.out.println("At least one tourist is required!");
            return;
        }

        scheduleService.addSchedule(new Schedule(tourName, dateTime, selectedGuides, selectedTourists));
        System.out.println("New schedule created successfully!");
    }

    // --- Edit Methods ---
    private void editTourist() {
        if (touristService.getAllTourists().isEmpty()) {
            System.out.println("No tourists available to edit.");
            return;
        }

        System.out.println("----- EDIT TOURIST INFORMATION -----");
        for (int i = 0; i < touristService.getAllTourists().size(); i++) {
            Tourist t = touristService.getAllTourists().get(i);
            int age = t.getAge();
            String ageStr = (age >= 0) ? String.valueOf(age) : "Not set";
            System.out.println("[" + (i + 1) + "] " + touristService.getAllTourists().get(i).displayInfo());
        }

        int index = getIntInput("Select Tourist number to edit: ");

        if (index < 1 || index > touristService.getAllTourists().size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Tourist t = touristService.getAllTourists().get(index - 1);

        System.out.print("New Firstname (" + t.getFirstName() + "): ");
        String newFirst = sc.nextLine();
        if (!newFirst.trim().isEmpty()) {
            t.setFirstName(newFirst);
        }
        
        System.out.print("New Lastname (" + t.getLastName() + "): ");
        String newLast = sc.nextLine();
        if (!newLast.trim().isEmpty()) {
            t.setLastName(newLast);
        }
        
        System.out.print("New Address (" + t.getAddress() + "): ");
        String newAddr = sc.nextLine();
        if (!newAddr.trim().isEmpty()) {
            t.setAddress(newAddr);
        }
        
        System.out.print("New Nationality (" + t.getNationality() + "): ");
        String newNat = sc.nextLine();
        if (!newNat.trim().isEmpty()) {
            t.setNationality(newNat);
        }
        
        System.out.print("New Birthday (" + t.getBirthday() + ") [Press Enter to keep current]: ");
        String newBirthday = sc.nextLine();
        
        if (!newBirthday.trim().isEmpty()) {
            // Validate the new birthday
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                java.time.LocalDate.parse(newBirthday, formatter);
                t.setBirthday(newBirthday);
                System.out.println("Birthday updated!");
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format! Birthday not changed.");
            }
        }
        
        System.out.println("Tourist information updated successfully!");
        System.out.println("Current age: " + t.getAge());
    }

    private void editGuide() {
        if (guideService.getAllGuides().isEmpty()) {
            System.out.println("No guides available to edit.");
            return;
        }

        System.out.println("----- EDIT GUIDE INFORMATION -----");
        for (int i = 0; i < guideService.getAllGuides().size(); i++) {
          System.out.println("[" + (i + 1) + "] " + guideService.getAllGuides().get(i).displayInfo());
        }

        int index = getIntInput("Select Guide number to edit: ");

        if (index < 1 || index > guideService.getAllGuides().size()) {
            System.out.println("---Invalid choice! input the existing number.----");
            return;
        }

        Guide g = guideService.getAllGuides().get(index - 1);

        System.out.print("New Firstname (" + g.getFirstName() + "): ");
        String newFirst = sc.nextLine();
        if (!newFirst.trim().isEmpty()) {
            g.setFirstName(newFirst);
        }
        
        System.out.print("New Lastname (" + g.getLastName() + "): ");
        String newLast = sc.nextLine();
        if (!newLast.trim().isEmpty()) {
            g.setLastName(newLast);
        }
        
        System.out.print("New Address (" + g.getAddress() + "): ");
        String newAddr = sc.nextLine();
        if (!newAddr.trim().isEmpty()) {
            g.setAddress(newAddr);
        }
        
        System.out.print("New Languages (" + g.getLanguages() + "): ");
        String newLang = sc.nextLine();
        if (!newLang.trim().isEmpty()) {
            g.setLanguages(newLang);
        }

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

        int index = getIntInput("Select Worker number to edit: ");

        if (index < 1 || index > workerService.getAllWorkers().size()) {
            System.out.println("---Invalid choice! input the existing number.----");
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

    // Helper methods for editSchedule
    private void manageScheduleGuides(Schedule schedule) {
        System.out.println("\n===== MANAGE GUIDES =====");
        System.out.println("Current Guides:");
        for (int i = 0; i < schedule.getGuides().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + schedule.getGuides().get(i).getFullName());
        }
        
        System.out.println("\n[1] Add Guide");
        System.out.println("[2] Remove Guide");
        int choice = getIntInput("Enter choice: ");
        
        switch (choice) {
            case 1 -> addGuidesToSchedule(schedule);
            case 2 -> {
                if (schedule.getGuides().size() <= 1) {
                    System.out.println("Cannot remove all guides. At least one guide is required.");
                    return;
                }
                int guideIndex = getIntInput("Enter guide number to remove: ");
                if (guideIndex > 0 && guideIndex <= schedule.getGuides().size()) {
                    Guide removed = schedule.getGuides().remove(guideIndex - 1);
                    System.out.println("Removed guide: " + removed.getFullName());
                } else {
                    System.out.println("Invalid guide number!");
                }
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    private void manageScheduleTourists(Schedule schedule) {
        System.out.println("\n===== MANAGE TOURISTS =====");
        System.out.println("Current Tourists:");
        for (int i = 0; i < schedule.getTourists().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + schedule.getTourists().get(i).getFullName());
        }
        
        System.out.println("\n[1] Add Tourist");
        System.out.println("[2] Remove Tourist");
        int choice = getIntInput("Enter choice: ");
        
        switch (choice) {
            case 1 -> addTouristsToSchedule(schedule);
            case 2 -> {
                if (schedule.getTourists().isEmpty()) {
                    System.out.println("No tourists to remove.");
                    return;
                }
                int touristIndex = getIntInput("Enter tourist number to remove: ");
                if (touristIndex > 0 && touristIndex <= schedule.getTourists().size()) {
                    Tourist removed = schedule.getTourists().remove(touristIndex - 1);
                    System.out.println("Removed tourist: " + removed.getFullName());
                } else {
                    System.out.println("Invalid tourist number!");
                }
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    private void editSchedule() {
        if (scheduleService.getAllSchedules().isEmpty()) {
            System.out.println("No schedules available to edit.");
            return;
        }
        
        System.out.println("----- EDIT SCHEDULE -----");
        for (int i = 0; i < scheduleService.getAllSchedules().size(); i++) {
            Schedule s = scheduleService.getAllSchedules().get(i);
            System.out.println("[" + (i + 1) + "] " + s.getTourName() + 
                              " (" + s.getDateTime() + ")");
        }

        int index = getIntInput("Select Schedule number to edit (0 to cancel): ");
        if (index == 0) {
            System.out.println("Cancelled.");
            return;
        }
        
        if (index < 1 || index > scheduleService.getAllSchedules().size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Schedule s = scheduleService.getAllSchedules().get(index - 1);
        
        System.out.println("\nCurrent Schedule Details:");
        s.displayScheduleDetails();
        
        System.out.println("\n[1] Edit Tour Name");
        System.out.println("[2] Edit Date/Time");
        System.out.println("[3] Manage Guides");
        System.out.println("[4] Manage Tourists");
        System.out.println("[0] Back");
        
        int choice = getIntInput("What do you want to edit? ");
        
        switch (choice) {
            case 1 -> {
                System.out.print("New Tour Name (" + s.getTourName() + "): ");
                s.setTourName(sc.nextLine());
            }
            case 2 -> {
                System.out.print("New Date/Time (" + s.getDateTime() + "): ");
                s.setDateTime(sc.nextLine());
            }
            case 3 -> manageScheduleGuides(s);
            case 4 -> manageScheduleTourists(s);
            case 0 -> {}
            default -> System.out.println("Invalid choice!");
        }
        
        System.out.println("Schedule updated successfully!");
    }

    // --- Delete Methods ---
    private void deleteTourist() {
        ArrayList<Tourist> list = touristService.getAllTourists();

        if (list.isEmpty()) {
            System.out.println("No tourists available.");
            return;
        }

        System.out.println("----- DELETE TOURIST -----");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + list.get(i).displayInfo());
        }

        int choice = getIntInput("Select tourist number to delete (0 to cancel): ");

        if (choice == 0) {
            System.out.println("Deletion cancelled.");
            return;
        }

        if (choice < 1 || choice > list.size()) {
            System.out.println("---Invalid choice! input the existing number.----");
            return;
        }

        Tourist removed = list.remove(choice - 1);
        System.out.println("Deleted Tourist: " + removed.getFullName());
    }

    private void deleteGuide() {
        ArrayList<Guide> list = guideService.getAllGuides();

        if (list.isEmpty()) {
            System.out.println("No guides available.");
            return;
        }

        System.out.println("----- DELETE GUIDE -----");
        for (int i = 0; i < list.size(); i++) {
           System.out.println("[" + (i + 1) + "] " + list.get(i).displayInfo());
        }

        int choice = getIntInput("Select guide number to delete (0 to cancel): ");

        if (choice == 0) {
            System.out.println("Deletion cancelled.");
            return;
        }

        if (choice < 1 || choice > list.size()) {
            System.out.println("---Invalid choice! input the existing number.----");
            return;
        }

        Guide removed = list.remove(choice - 1);
        System.out.println("Deleted Guide: " + removed.getFullName());
    }

    private void deleteWorker() {
        ArrayList<Worker> list = workerService.getAllWorkers();

        if (list.isEmpty()) {
            System.out.println("No workers available.");
            return;
        }

        System.out.println("----- DELETE WORKER -----");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + list.get(i).displayInfo());
        }

        int choice = getIntInput("Select worker number to delete (0 to cancel): ");

        if (choice == 0) {
            System.out.println("Deletion cancelled.");
            return;
        }

        if (choice < 1 || choice > list.size()) {
            System.out.println("---Invalid choice! input the existing number.----");
            return;
        }

        Worker removed = list.remove(choice - 1);
        System.out.println("Deleted Worker: " + removed.getFullName());
    }

    private void deleteSchedule() {
        ArrayList<Schedule> list = scheduleService.getAllSchedules();

        if (list.isEmpty()) {
            System.out.println("No schedules available.");
            return;
        }

        System.out.println("----- DELETE SCHEDULE -----");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + list.get(i).getTourName() +
                               " (" + list.get(i).getDateTime() + ")");
        }

        int choice = getIntInput("Select schedule number to delete (0 to cancel): ");

        if (choice == 0) {
            System.out.println("Deletion cancelled.");
            return;
        }

        if (choice < 1 || choice > list.size()) {
            System.out.println("---Invalid choice! input the existing number.----");
            return;
        }

        Schedule removed = list.remove(choice - 1);
        System.out.println("Deleted Schedule: " + removed.getTourName());
    }

    // --- Utility Methods ---
    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Invalid input! Only numbers allowed.");
            }
        }
    }
}