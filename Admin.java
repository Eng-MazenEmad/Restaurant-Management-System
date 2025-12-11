import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

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

    public abstract void updateUserInfo(int userId, String newUserName, String newPassword) ;

}
class Admin extends User {

    //customer class -> ahmed ali
//    ArrayList<Customer> customers = new ArrayList<>();

    // Constructor
    public Admin(int userId, String userName, String password, int role)  {
        super(userId, userName, password, role);
        try{
            String fileName = "";

            if (role == 1) {
                fileName="admin.txt";
            }
            else if(role == 2){
                fileName = "employee.txt";
            }
            try (BufferedWriter BW = new BufferedWriter(new FileWriter(fileName, true))) {
                String adminInfo = userId + "," + userName + "," + password;
                BW.write(adminInfo);
                BW.newLine();
            }
        }catch (IOException ex){
            System.out.println("An I/O error occurred: "+ex.getMessage());
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
    //_______________________________ DONE ____________________________________

    // Read employees
    private ArrayList<String> readEmployees()  {
        ArrayList<String> employees = new ArrayList<>();
        File file = new File("employee.txt");
        try{
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
            System.out.println("File not found: "+file+", "+e.getMessage());
        } catch (IOException ex) {
            System.out.println("An I/O error occurred: "+ex.getMessage());
            ex.printStackTrace();
        }
        return employees;
    }

    // Add employee
    public void addEmployee(int id, String username, String password)  {
        String employeeInfo = id + "," + username + "," + password + ",2";
        File file = new File("employee.txt");
        try{
            try(BufferedWriter BW = new BufferedWriter(new FileWriter(file, true))){
            BW.write(employeeInfo);
            BW.newLine();
            }
            System.out.println("Employee added successfully");
        }catch (IOException ex){
            System.out.println("An I/O error occurred: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    //delete employee
    public boolean deleteEmployee(int id){
        ArrayList<String> employees = readEmployees();
        boolean flag = false;
        try{
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
        }catch (IOException ex){
            System.out.println("An I/O error occurred: "+ex.getMessage());
            ex.printStackTrace();
        }

        if (flag) {
            System.out.println("Employee deleted successfully");
        } else {
            System.out.println("Employee not found");
        }
        return flag;
    }

    //update employee-alter username and password

    public void updateEmployeeInfo(int id, String newUsername, String newPass) {
        boolean flag = false;
        try{
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
                if (flag) {
                    System.out.println("Employee updated successfully");
                } else {
                    System.out.println("Employee not found");
                }
            } catch (IOException e) {
                System.out.println("Error updating employee. " + e.getMessage());
            }
        catch (NumberFormatException e) {
                System.out.println("Error parsing employee ID: "+e.getMessage());
        }
    }
    public void updateInfoFromAdmin(String current_userName,int id,String new_userName,String new_pass){
        boolean isAdmin=false;
        boolean flag=false;
        ArrayList<String> admins=new ArrayList<>();
        try{
            try(BufferedReader br=new BufferedReader(new FileReader("admin.txt"))){
                String line;
                while ((line=br.readLine())!=null){
                    if(line.trim().equals(current_userName)){
                        isAdmin=true;
                        break;
                    }
                }
                if(!isAdmin){
                    System.out.println("You are not an admin. Access denied.");
                    return;
                } try(BufferedReader BR=new BufferedReader(new FileReader("admin.txt"))){
                    String lines;
                    while ((lines= BR.readLine())!=null){
                        if(lines.trim().isEmpty())
                            continue;
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
                } catch (FileNotFoundException f){
                    System.out.println("File not found. "+f.getMessage());
                }
                    } catch (IOException e){
            System.out.println("Error reading admins file "+e.getMessage());
            e.printStackTrace();
                }
            } catch (NumberFormatException e) {
            System.out.println("Error parsing admin ID: "+e.getMessage());
        }

        updateEmployeeInfo(id,new_userName,new_pass);
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
    public String searchEmployee(int id){
        ArrayList<String> employees = readEmployees();

        for (String employee : employees) {
            String[] parts = employee.split(",");
            if (Integer.parseInt(parts[0]) != id) {
                return "Employee not found.";
            } else {
                return "Employee Found - ID: " + parts[0] + ", Username: " + parts[1];
            }
        }
        return null;

    }



    public static class FileUtil {
        public static List<String[]> readRecords(String filePath){
            List<String[]> rows=new ArrayList<>();
            try(BufferedReader br=new BufferedReader(new FileReader(filePath))){
                String line;
                while ((line= br.readLine())!=null){
                    if (line.trim().isEmpty()) continue;
                    rows.add(line.split(",",-1));
                }

            }catch (Exception e){
                System.out.println("File Error: "+e.getMessage());
            }
            return rows;
        }
        public static void addRecord(String path,String record){
            try(BufferedWriter bw=new BufferedWriter(new FileWriter(path,true))){
                bw.write(record);
                bw.newLine();
            }catch (Exception e){
                System.out.println("Write Error: "+e.getMessage());
            }
        }
    }
    public static class Report {
        protected String filePath;

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
        public  void generate(String mode,String queryID,String ... newdata){};

    }
     static class EmployeeReport extends Report{
         public EmployeeReport() {
             super.setFilePath("employee.txt");
         }
        @Override
        public void generate(String mode, String queryID,String ... newdata) {
            List<String[]> rows=FileUtil.readRecords(filePath);
            if(mode.equals("DISPLAY")) {
                System.out.println("\t Employee Report");
                for (String[] r : rows) {
                    String id = r[0], name = r[1], role = r[2];
                    if (!queryID.equals("ALL") && !id.equals(queryID))
                        continue;
                    System.out.println("ID: " + id + ", Name: " + name + ", Role: " + role );
                }
            }else if(mode.equals("WRITE")){
                String record=String.join(",",newdata);
                FileUtil.addRecord(filePath,record);
                System.out.println("New Employee Record Added");
            }else{
                System.out.println("Invalid mode: Use DISPLAY or WRITE");
            }
        }
    }

    static class CustomerReport extends Report{
        public CustomerReport() {
            super.setFilePath("customer.txt");
        }

        public void generate(String mode, String queryID,String ... newdata) {
            List<String[]> rows = FileUtil.readRecords(filePath);
            if (mode.equals("DISPLAY")) {
                System.out.println("\t Customer Report");
                for (String[] r : rows) {
                    String id = r[0], name = r[1], phone = r[2], points = r[3];
                    if (!queryID.equals("ALL") && !id.equals(queryID))
                        continue;
                    System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Points: " + points);
                }
            }else if(mode.equals("WRITE")){
                String record=String.join(",",newdata);
                FileUtil.addRecord(filePath,record);
                System.out.println("New Customer Record Added");
            }else{
                System.out.println("Invalid mode: Use DISPLAY or WRITE");
            }
        }
    }




//try (BufferedWriter BW = new BufferedWriter(new FileWriter("admin.txt",true)))
//    {
//        String lines;
//        while((lines= BR.readLine())!=null){
//            if(lines.trim().isEmpty())
//                continue;
//            String[] parts=lines.split(",");
//            if(Integer.parseInt(parts[0]) == id){
//                BW.write(id + "," + new_userName + "," + new_pass);
//                BW.newLine();
//                flag = true;
//            }else {
//                BW.write(lines);
//                BW.newLine();
//            }
//        }
//    }



}
//_______________________________ DONE ____________________________________

//    public ArrayList<String> listEmployee() {
//        ArrayList<String> employeeInfo = new ArrayList<>();
//        for (Employee e : employees) {
//            employeeInfo.add("Username: " + e.getUsername() + "  " + "ID: " + e.getUserId());
//        }
//        return employeeInfo;
//    }
//
//    public String searchEmployee(int id) {
//        for (Employee e : employees) {
//            if (id == e.getUserId()) {
//                return e.username;
//            }
//        }
//        System.out.println("Don't exist");
//        return null;
//    }
//
//    public ArrayList<String> generateReports() {
//        ArrayList<String> report = new ArrayList<>();
//        report.add("Total employee: " + employees.size());
//        for (Employee e : employees) {
//            report.add("Name: " + e.getUsername() + "ID: " + e.getUserId());
//        }
//        //Ahmed Ali
//        report.add("Total customer: " + customers.size());
//        for (Customer c : customers) {
//            report.add("Name: " + c.getCustName() + "ID: " + c.getCustID()
//                    + "Phone: " + c.getPhoneNum() + "Address: " + c.getAddress()
//                    + "Loyalty points: " + c.getLoyaltyPoints()
//                    + "Total spent: " + c.getTotalSpent());
//        }
//        return report;
//    }
//}

