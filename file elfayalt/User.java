package com.mycompany.el_hamm;

import java.io.*;
import java.util.*;

abstract class User {

    protected int userId;
    protected String username;
    protected String password;
    protected int role;

    // Constructors
    public User() {
    }

    public User(int userId, String username, String password, int role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPass(String password) {
        return this.password.equals(password);
    }

    // Login and logout functionality
    public boolean login(String username, String password) {
        return (this.checkPass(password) && this.getUsername().equals(username));
    }

    public void logout() {
        System.out.println("Logged out successfully.");
    }

    public abstract int getRole();

    public abstract void updateUserInfo(int userId, String newUserName, String newPassword);

}

class Admin extends User {

    //customer class -> ahmed ali
//    ArrayList<Customer> customers = new ArrayList<>();
    // Constructor
    public Admin(int userId, String userName, String password, int role) {
        super(userId, userName, password, role);
        try {
            String fileName = "";

            if (role == 1) {
                fileName = "admin.txt";
            } else if (role == 2) {
                fileName = "employee.txt";
            }
            try (BufferedWriter BW = new BufferedWriter(new FileWriter(fileName, true))) {
                String adminInfo = userId + "," + userName + "," + password;
                BW.write(adminInfo);
                BW.newLine();
            }
        } catch (IOException ex) {
            System.out.println("An I/O error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public int getRole() {
        return 1;
    }

    @Override
    public void updateUserInfo(int userId, String newUserName, String newPassword) {
        // الأدمن هنا بيعدل بياناته
        if (this.userId == userId) {
            this.username = newUserName;
            this.password = newPassword;
            System.out.println("Admin updated his own info successfully");
        }
    }

    // Read employees
    private ArrayList<String> readEmployees() {
        ArrayList<String> employees = new ArrayList<>();
        File file = new File("employee.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
                return employees;
            } else {
                try (BufferedReader BR = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = BR.readLine()) != null) {
                        employees.add(line);
                    }
                }  // closable with try method

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file + ", " + e.getMessage());
        } catch (IOException ex) {
            System.out.println("An I/O error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
        return employees;
    }

    // Add employee
    public void addEmployee(int id, String username, String password) {
        String employeeInfo = id + "," + username + "," + password;
        File file = new File("employee.txt");
        try {
            try (BufferedWriter BW = new BufferedWriter(new FileWriter(file, true))) {
                BW.write(employeeInfo);
                BW.newLine();
                BW.flush();
            }
//            System.out.println("Employee added successfully");
        } catch (IOException ex) {
//            System.out.println("An I/O error occurred: " + ex.getMessage());
//            ex.printStackTrace();
        }
    }

    //delete employee
    public boolean deleteEmployee(int id) {
        ArrayList<String> employees = readEmployees();
        boolean flag = false;
        try {
            try (BufferedWriter BW = new BufferedWriter(new FileWriter("employee.txt"));) {
                for (String lineOfEmp : employees) {
                    String[] parts = lineOfEmp.split(",");
                    if (Integer.parseInt(parts[0]) == id) {
                        flag = true;
                    } else {
                        BW.write(lineOfEmp);
                        BW.newLine();
                    }
                }
            }
        } catch (IOException ex) {
//            System.out.println("An I/O error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }

        if (flag) {
//            System.out.println("Employee deleted successfully");
        } else {
//            System.out.println("Employee not found");
        }
        return flag;
    }

    //update employee-alter username and password
    public boolean updateEmployeeInfo(int id, String newUsername, String newPass) {
        boolean flag = false;
        try {
            ArrayList<String> employees = readEmployees();
            try (BufferedWriter BW = new BufferedWriter(new FileWriter("employee.txt"))) {
                for (String lineOfEmp : employees) {
                    String[] parts = lineOfEmp.split(",");
                    if (Integer.parseInt(parts[0]) == id) {
                        BW.write(id + "," + newUsername + "," + newPass);
                        BW.newLine();
                        flag = true;
                    } else {
                        BW.write(lineOfEmp);
                        BW.newLine();
                    }
                }
            }
            return flag;
//            if (flag) {
//                System.out.println("Employee updated successfully");
//            } else {
//                System.out.println("Employee not found");
//            }
        } catch (IOException e) {
//            System.out.println("Error updating employee. " + e.getMessage());
        } catch (NumberFormatException e) {
//            System.out.println("Error parsing employee ID: " + e.getMessage());
        }
        return false;
    }

    public void updateInfoFromAdmin(String current_userName, int id, String new_userName, String new_pass) {
        boolean isAdmin = false;
        boolean flag = false;
        ArrayList<String> admins = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("admin.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().equals(current_userName)) {
                        isAdmin = true;
                        break;
                    }
                }
                if (!isAdmin) {
                    System.out.println("You are not an admin. Access denied.");
                    return;
                }
                try (BufferedReader BR = new BufferedReader(new FileReader("admin.txt"))) {
                    String lines;
                    while ((lines = BR.readLine()) != null) {
                        if (lines.trim().isEmpty()) {
                            continue;
                        }
                        admins.add(lines);
                    }
                    for (int i = 0; i < admins.size(); i++) {
                        String[] parts = admins.get(i).split(",");
                        if (parts.length >= 3 && Integer.parseInt(parts[0]) == id) {
                            admins.set(i, id + "," + new_userName + "," + new_pass);
                            flag = true;
                            break;
                        }
                    }
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("admin.txt"))) {
                        for (String s : admins) {
                            bw.write(s);
                            bw.newLine();
                        }
                    }

                    if (flag) {
                        System.out.println("Admin updated successfully");
                    } else {
                        System.out.println("Admin not found");
                    }
                } catch (FileNotFoundException f) {
                    System.out.println("File not found. " + f.getMessage());
                }
            } catch (IOException e) {
                System.out.println("Error reading admins file " + e.getMessage());
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing admin ID: " + e.getMessage());
        }

        updateEmployeeInfo(id, new_userName, new_pass);
    }

    // List Employees
    public ArrayList<String> listEmployees() {
        ArrayList<String> employeeInfo = new ArrayList<>();
        ArrayList<String> employees = readEmployees();
        for (String employee : employees) {
            String[] parts = employee.split(",");
            if (parts.length >= 2) {
                employeeInfo.add("ID: " + parts[0] + ", Username: " + parts[1]);
            }
        }
        return employeeInfo;
    }

    // Search Employee
    public String searchEmployee(int id) {
        ArrayList<String> employees = readEmployees();

        for (String employee : employees) {
            String[] parts = employee.split(",");
            if (Integer.parseInt(parts[0]) == id) {
                return "Employee Found - ID: " + parts[0] + ", Username: " + parts[1];

            } else {
//                return "Employee not found.";

            }
        }
        return null;

    }

    //-------------------------------------------------------------------------------------------------
    static class Meal {

        private int MealId;
        private String name;
        private double price;

        public Meal(int MealId, String name, double price) {
            this.MealId = MealId;
            this.name = name;
            this.price = price;
        }

        public int getMealID() {
            return MealId;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String toFileString() {
            return MealId + "," + name + "," + String.format("%.2f", price);
        }

        @Override
        public String toString() {
            return MealId + "," + name + "," + price;
        }

        Object getId() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }

    public static class MealManager {

        private static final String FILE_PATH = "meals.txt";

        public static List<Meal> readMeals() {
            List<Meal> meals = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;

                while ((line = br.readLine()) != null) {

                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }

                    String[] parts = line.split(",");
                    if (parts.length != 3) {
                        continue;
                    }

                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        double price = Double.parseDouble(parts[2].trim());

                        meals.add(new Meal(id, name, price));
                    } catch (NumberFormatException e) {
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("FILE NOT FOUND: " + FILE_PATH);
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }

            return meals;
        }

        public static void writeMeals(List<Meal> meals) {
            try {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
                    for (Meal meal : meals) {
                        bw.write(meal.toFileString());
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("IOException. " + " " + e.getMessage());
            }
        }

        public static boolean addMeal(int id, String name, double price) {
            List<Meal> meals = readMeals();
            for (Meal meal : meals) {
                if (meal.getMealID() == id) {
                    return false;
                }
            }
            meals.add(new Meal(id, name, price));
            writeMeals(meals);
            return true;
        }

        public static boolean deleteMeal(int id) {
            List<Meal> meals = readMeals();
            boolean Delete = false;
            for (int i = meals.size() - 1; i >= 0; i--) {
                Meal meal = meals.get(i);

                if (meal.getMealID() == id) {
                    meals.remove(i);
                    Delete = true;
                    break;
                }
            }
            if (Delete) {
                writeMeals(meals);
            }
            return Delete; // ليها لازمة فى الGUI عشان اقوله نجح الحذف او التعديل ولا لا
        }

        public static boolean updateMeal(int id, String newName, double newPrice) {
            List<Meal> meals = readMeals();
            boolean found = false;

            for (Meal meal : meals) {
                if (meal.getMealID() == id) {
                    meal.setName(newName);
                    meal.setPrice(newPrice);
                    found = true;
                    break;
                }
            }

            if (found) {
                writeMeals(meals);
            }
            return found; // نفس الكلام شبه delete
        }

        public static List<Meal> searchMeal(String searchKey) {

            List<Meal> allMeals = readMeals();
            List<Meal> results = new ArrayList<>();

            String key = searchKey.trim().toLowerCase();

            // بحث بالاسم
            for (Meal meal : allMeals) {
                if (meal.getName().toLowerCase().contains(key)) {
                    results.add(meal);
                }
            }

            // بحث بالرقم — بدون أي طباعة خطأ
            try {
                int idSearch = Integer.parseInt(key);
                for (Meal meal : allMeals) {
                    if (meal.getMealID() == idSearch) {
                        results.add(meal);
                    }
                }
            } catch (NumberFormatException e) {
                // تجاهل بصمت — بدون رسائل نهائياً
            }

            return results;
        }
    }

    //-------------------------------------------------------------------------------------------------
    public static class FileUtil {

        public static List<String[]> readRecords(String filePath) {
            List<String[]> rows = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    rows.add(line.split(",", -1));
                }

            } catch (Exception e) {
                System.out.println("File Error: " + e.getMessage());
            }
            return rows;
        }

        public static void addRecord(String path, String record) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
                bw.write(record);
                bw.newLine();
            } catch (Exception e) {
                System.out.println("Write Error: " + e.getMessage());
            }
        }
    }

    public static class Report {

        protected String filePath;

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public void generate(String mode, String queryID, String... newdata) {
        }
    ;

    }
     static class EmployeeReport extends Report {

        public EmployeeReport() {
            super.setFilePath("employee.txt");
        }

        @Override
        public void generate(String mode, String queryID, String... newdata) {
            List<String[]> rows = FileUtil.readRecords(filePath);
            if (mode.equals("DISPLAY")) {
                System.out.println("\t Employee Report");
                for (String[] r : rows) {
                    String id = r[0], name = r[1], role = r[2];
                    if (!queryID.equals("ALL") && !id.equals(queryID)) {
                        continue;
                    }
                    System.out.println("ID: " + id + ", Name: " + name + ", Role: " + role);
                }
            } else if (mode.equals("WRITE")) {
                String record = String.join(",", newdata);
                FileUtil.addRecord(filePath, record);
                System.out.println("New Employee Record Added");
            } else {
                System.out.println("Invalid mode: Use DISPLAY or WRITE");
            }
        }
    }

    static class CustomerReport extends Report {

        public CustomerReport() {
            super.setFilePath("customer.txt");
        }

        @Override
        public void generate(String mode, String queryID, String... newdata) {
            List<String[]> rows = FileUtil.readRecords(filePath);
            if (mode.equals("DISPLAY")) {
                System.out.println("\t Customer Report");
                for (String[] r : rows) {
                    String id = r[0], name = r[1], phone = r[2], points = r[3];
                    if (!queryID.equals("ALL") && !id.equals(queryID)) {
                        continue;
                    }
                    System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Points: " + points);
                }
            } else if (mode.equals("WRITE")) {
                String record = String.join(",", newdata);
                FileUtil.addRecord(filePath, record);
                System.out.println("New Customer Record Added");
            } else {
                System.out.println("Invalid mode: Use DISPLAY or WRITE");
            }
        }
    }

//_______________________________ DONE ____________________________________
// بصو هتلاقو اني عاملة اتنين فانكشن واحدة للكونسول عشان اشوف هي شغالة و لا لا و التانية عشان لما نيجي نستخدم ال gui
//  Create offer
    public void createSimpleOffer(int id, String offerName, String discount) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("offers.txt", true))) {
            String offer = "Customer ID: " + id + ", Offer: " + offerName + ", Discount: " + discount;
            bw.write(offer);
            bw.newLine();
//            System.out.println("offer created: " + offerName);
        }
    }

//    public String createSimpleOffer(String offerName, String discount) {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter("offers.txt", true))) {
//            String offer = "OFFER: " + offerName + " - Discount: " + discount;
//            bw.write(offer);
//            bw.newLine();
//            return offer;  
//        } catch (IOException e) {
//            return "Problem creating offer: " + e.getMessage();
//        }
//    }
//  Make marketing
    public void sendAnnouncement(String message) {
        System.out.println("===== Restaurant Announcement =====");
        System.out.println(message);
        System.out.println("========================");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("announcements.txt", true))) {
            bw.write("Announcement: " + message);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Problem saving announcement");
        }
    }

//public String sendAnnouncement(String message) {
//    String announcement = "Announcement: " + message;
//    
//    try (BufferedWriter bw = new BufferedWriter(new FileWriter("announcements.txt", true))) {
//        bw.write(announcement);
//        bw.newLine();
//        return announcement;
//    } catch (IOException e) {
//        return "Error sending announcement";
//    }
//}
// make reward
    public void giveReward(int customerId, String reward) {
//        System.out.println("Customer ID: " + customerId + ", Reward: " + reward);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("rewards_given.txt", true))) {
            String record = "Customer: " + customerId + ", Reward: " + reward;
            bw.write(record);
            bw.newLine();
        } catch (IOException e) {
//            System.out.println(" Problem saving reward");
        }
    }

//    public String giveReward(int customerId, String reward) {
//        String rewardMessage = "Reward for Customer " + customerId + ": " + reward;
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter("rewards_given.txt", true))) {
//            String record = "Customer " + customerId + " got: " + reward;
//            bw.write(record);
//            bw.newLine();
//            return rewardMessage;
//        } catch (IOException e) {
//            return "Error giving reward";
//        }
//      
//    }
}
