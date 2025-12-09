package Customer;

import java.io.FileWriter;

public class Customer {

    private int custID;
    private String custName;
    private String phoneNum;
    private String address;
    private int loyaltyPoints;
    private double totalSpent;

    private boolean successful;        // true only if created correctly
    private static int idCounter = 0;

    // ---------- Constructor ----------
    public Customer(String custName, String phoneNum, String address) {

        successful = true;

        // Name validation (letters and spaces only)
        if (custName.matches("[a-zA-Z ]+")) {
            this.custName = custName;
        } else {
            System.out.println("Invalid customer name!");
            successful = false;
        }

        // Phone validation (exactly 11 digits)
        if (phoneNum.matches("\\d{11}")) {
            this.phoneNum = phoneNum;
        } else {
            System.out.println("Invalid phone number!");
            successful = false;
        }

        this.address = address;

        // Assign ID only if data is valid
        if (successful) {
            idCounter++;
            custID = idCounter;
        }
    }

    // ---------- Setters ----------
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

    public double getTotalSpent() {
        return totalSpent;
    }

    public boolean isSuccessful() {
        return successful;
    }

    // ---------- Display ----------
    public void viewProfile() {
        System.out.println(
                "Customer ID: " + custID +
                "\nName: " + custName +
                "\nPhone Number: " + phoneNum +
                "\nAddress: " + address +
                "\nLoyalty Points: " + loyaltyPoints +
                "\nTotal Spent: " + totalSpent
        );
    }

    // ---------- Save to File ----------
    public void saveToFile() {

        if (!successful) {
            System.out.println("Customer data is invalid. Not saved.");
            return;
        }

        try {
            FileWriter fw = new FileWriter("customers.txt", true);

            fw.write("Customer ID: " + custID + "\n");
            fw.write("Name: " + custName + "\n");
            fw.write("Phone: " + phoneNum + "\n");
            fw.write("Address: " + address + "\n");
            fw.write("Loyalty Points: " + loyaltyPoints + "\n");
            fw.write("Total Spent: " + totalSpent + "\n");
            fw.write("---------------------------\n");

            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving customer!");
        }
    }
}
