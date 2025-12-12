/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.el_hamm;

import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class Login extends javax.swing.JDialog {

    abstract class User {

        protected int userId;
        protected String username;
        protected String password;
        protected int role;

        // Constructors
        public User() {
        }

        public User(int userId, String username, String password) {
            this.userId = userId;
            this.username = username;
            this.password = password;
        }

        public User(int userId, String username, String password, int role) {
            this(userId, username, password);
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
        public Admin(int userId, String userName, String password) {
            super(userId, userName, password);
        }
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
        //_______________________________ DONE ____________________________________

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
            String employeeInfo = id + "," + username + "," + password + ",2";
            File file = new File("employee.txt");
            try {
                try (BufferedWriter BW = new BufferedWriter(new FileWriter(file, true))) {
                    BW.write(employeeInfo);
                    BW.newLine();
                }
                System.out.println("Employee added successfully");
            } catch (IOException ex) {
                System.out.println("An I/O error occurred: " + ex.getMessage());
                ex.printStackTrace();
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
                System.out.println("An I/O error occurred: " + ex.getMessage());
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
                if (flag) {
                    System.out.println("Employee updated successfully");
                } else {
                    System.out.println("Employee not found");
                }
            } catch (IOException e) {
                System.out.println("Error updating employee. " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error parsing employee ID: " + e.getMessage());
            }
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
                if (Integer.parseInt(parts[0]) != id) {
                    return "Employee not found.";
                } else {
                    return "Employee Found - ID: " + parts[0] + ", Username: " + parts[1];
                }
            }
            return null;

        }

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
    }

    /**
     * Creates new form Login
     */
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        username_field = new javax.swing.JTextField();
        id = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        id_field = new javax.swing.JTextField();
        login_btn = new javax.swing.JButton();
        pass_field = new javax.swing.JPasswordField();
        role_field = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Login"));
        jPanel1.setToolTipText("Restaurant");

        username.setText("Username:");

        username_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_fieldActionPerformed(evt);
            }
        });

        id.setText("ID:");

        jLabel2.setText("Password");
        jLabel2.setToolTipText("");

        id_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_fieldActionPerformed(evt);
            }
        });

        login_btn.setText("Login");
        login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnActionPerformed(evt);
            }
        });

        pass_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_fieldActionPerformed(evt);
            }
        });

        role_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Employee" }));
        role_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                role_fieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Role:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(username)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(username_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(role_field, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(login_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(role_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(login_btn)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void username_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_fieldActionPerformed

    }//GEN-LAST:event_username_fieldActionPerformed

    private void id_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_fieldActionPerformed

    }//GEN-LAST:event_id_fieldActionPerformed

    private void login_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_btnActionPerformed
        try {
            String username = username_field.getText();
            String id = id_field.getText();
            String pass = new String(pass_field.getPassword());
            if (!username.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
                throw new IllegalArgumentException("Username must be contain only letters and numbers");
            }
            if (!id.matches("\\d+")) {
                throw new IllegalArgumentException("ID must be contain only numbers");
            }
            Admin a = new Admin(Integer.parseInt(id), username, pass);
            if (a.login(username, pass)) {
                //new page
                //this.setVisible(false);
                if (role_field.getSelectedIndex() == 0) {
                    AdminDashboard ad = new AdminDashboard();
                    this.setVisible(false);
                    ad.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username, Password or ID is incorrect", "Login failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_login_btnActionPerformed

    private void pass_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_fieldActionPerformed
        //        String pass=new String(pass_field.getPassword());

        // TODO add your handling code here:
    }//GEN-LAST:event_pass_fieldActionPerformed

    private void role_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_role_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_role_fieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel id;
    private javax.swing.JTextField id_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login_btn;
    private javax.swing.JPasswordField pass_field;
    private javax.swing.JComboBox<String> role_field;
    private javax.swing.JLabel username;
    private javax.swing.JTextField username_field;
    // End of variables declaration//GEN-END:variables
}
