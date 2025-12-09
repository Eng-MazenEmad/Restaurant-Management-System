package Customer;


public class Customer {

   
    private int custID;
    private String custName;
    private String phoneNum;
    private String address;
    private int loyaltyPoints;
    private double totalSpent;
    private boolean successful=true;
    private static int idCounter = 0;

    
    

    public Customer(String custName, String phoneNum, String address) {
        if (custName.matches("\\D+")) {
            this.custName = custName;
        } else {

            System.out.println("invalid custName! it must contain characters only.");
            successful=false;
            
        }
       if (phoneNum.matches("\\d{11}")) {
            this.phoneNum = phoneNum;
        } else {
            System.out.println("Invalid phone number! It must contain digits only and exactly 11 digits.");
            successful=false;
            
            
        }
        this.address = address;
        if(successful){
        idCounter++;
        custID=idCounter;
        }

    }

    public void setName(String custName) {
         if (custName.matches("\\D+")) {
            this.custName = custName;
        } else {

            System.out.println("invalid custName! it must contain characters only.");
            successful=false;
            
        }
        
    }

    public void setPhoneNum(String phoneNum) {
        if (phoneNum.matches("\\d{11}")) {
            this.phoneNum = phoneNum;
        } else {
            System.out.println("Invalid phone number! It must contain digits only and exactly 11 digits.");
            successful=false;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public void viewProfile() {
        System.out.println(
                "Name: " + custName
                + "\nPhone Number: " + phoneNum
                + "\nAddress: " + address
                + "\nLoyalty Points: " + loyaltyPoints
                + "\nTotal Spent: " + totalSpent
                + "\nCustomer ID: " + custID
                + "\nNumber of orders: ");
    }
}
    
// code for saving cust info in files
//public void saveToFile() {
//    if (!successful) {
//        System.out.println("Customer data is invalid. Not saved.");
//        return;
//    }
//
//    try {
//        FileWriter fw = new FileWriter("customers.txt", true);
//        fw.write("Customer ID: " + custID + "\n");
//        fw.write("Name: " + custName + "\n");
//        fw.write("Phone: " + phoneNum + "\n");
//        fw.write("Address: " + address + "\n");
//        fw.write("Loyalty Points: " + loyaltyPoints + "\n");
//        fw.write("Total Spent: " + totalSpent + "\n");
//        fw.write("---------------------------\n");
//        fw.close();
//    } catch (Exception e) {
//        System.out.println("Error saving customer!");
//    }
//}

