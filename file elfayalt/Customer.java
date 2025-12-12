/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.el_hamm;

/**
 *
 * @author Deepfix
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customer {

    private int custID;
    private String custName;
    private String phoneNum;
    private String address;
    private int loyaltyPoints;
    private boolean successful;
    private static int idCounter;

    // Runs once when program starts to load last ID 
    static {
        loadLastIdFromFile();
    }

    // ---------- Constructor ----------
    public Customer(String custName, String phoneNum, String address) {
        successful = true; // assume valid initially

        // Trim inputs
        String trimmedName = custName.trim();
        String trimmedPhone = phoneNum.trim();
        String trimmedAddress = address.trim();

        // Validate name
        if (trimmedName.matches("[a-zA-Z ]+")) {
            this.custName = trimmedName;
        } else {
            System.out.println("Invalid customer name!");
            successful = false;
        }

        // Validate phone number
        if (trimmedPhone.matches("\\d{11}")) {
            this.phoneNum = trimmedPhone;
        } else {
            System.out.println("Invalid phone number!");
            successful = false;
        }

        // Assign address
        this.address = trimmedAddress;

        // Assign ID only if creation was successful
        if (successful) {
            idCounter++;
            custID = idCounter;
        }
    }

    // ---------- Setters ----------
    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setName(String custName) {
        if (custName.matches("[a-zA-Z ]+")) {
            this.custName = custName;
        } else {
            System.out.println("Invalid customer name!");
        }
    }

    public void setPhoneNum(String phoneNum) {
        if (phoneNum.matches("\\d{11}")) {
            this.phoneNum = phoneNum;
        } else {
            System.out.println("Invalid phone number!");
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    // ---------- Getters ----------
    public int getCustID() {
        return custID;
    }

    public String getName() {
        return custName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public boolean isSuccessful() {
        return successful;
    }

    // ---------- Display ----------
    public void viewProfile() {
        System.out.println(
                "Customer ID: " + custID
                + "\nName: " + custName
                + "\nPhone Number: " + phoneNum
                + "\nAddress: " + address
                + "\nLoyalty Points: " + loyaltyPoints
        );
    }
    // ---------- LOAD LAST ID ----------

    private static void loadLastIdFromFile() {
        File file = new File("customers.txt");

        if (!file.exists()) {
            idCounter = 0;
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.startsWith("Customer ID:")) {
                    int id = Integer.parseInt(
                            line.replace("Customer ID:", "").trim()
                    );
                    if (id > idCounter) {
                        idCounter = id;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------- Save to File ----------
    public void saveToFile() {
        if (!successful) {
            System.out.println("Customer data is invalid. Not saved.");
            return;
        }

        try (FileWriter fw = new FileWriter("customers.txt", true)) {
            fw.write("Customer ID: " + custID + "\n");
            fw.write("Name: " + custName + "\n");
            fw.write("Phone: " + phoneNum + "\n");
            fw.write("Address: " + address + "\n");
            fw.write("Loyalty Points: " + loyaltyPoints + "\n");
            fw.write("---------------------------\n");
        } catch (Exception e) {
            System.out.println("Error saving customer!");
        }
    }
//    clear all customers

    public static void clearCustomersFile() {
        try (FileWriter fw = new FileWriter("customers.txt", false)) {
            idCounter = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean idExists(int targetID) {
        try {
            File file = new File("customers.txt");
            if (!file.exists()) {
                return false;
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.startsWith("Customer ID:")) {
                    int id = Integer.parseInt(line.replace("Customer ID:", "").trim());
                    if (id == targetID) {
                        sc.close();
                        return true;
                    }
                }
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
