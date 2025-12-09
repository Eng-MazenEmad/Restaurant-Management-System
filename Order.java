

import java.util.*;
import java.io.*;

public class Order {
    private int orderId;
    private int customerId;
    private boolean status;
    private double totalAmount;//total price 
    private ArrayList<OrderDetails> items;
    private static int numOfOrders;
    private double totalAmountForOrder;

    public Order(int orderId) {
        this.orderId = orderId;
        this.status = false;
        this.totalAmount = 0.0;
        this.items = new ArrayList<>();
    }

    /// //////////////methods//////////////////
    /// OrderDetails pass it
    public void makeOrder(OrderDetails orderedItem) {
        this.items.add(orderedItem);
        this.totalAmount += orderedItem.getQuantity() * orderedItem.getMealPrice();
        completeOrderStatus();
    }


    public void cancelOrder() {
        items.clear();
        numOfOrders--;
        deleteOrderFromFile(orderId);
        this.status = false;
        totalAmount = 0;
    }

    private void completeOrderStatus() {
        this.status = !this.items.isEmpty();
        numOfOrders++;
    }

    public void saveOrder() throws FileNotFoundException {
        addToFile();
    }

    /// ////////////seters////////////////////
    public void setStatus(boolean status) {
        this.status = status;
    }

    //Ahmed class
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setTotalAmountByCustomerId(double total) {
        this.totalAmountForOrder = total;
    }

    /// ////////////////getters////////////////
    public int getOrderId() {
        return orderId;
    }

    public boolean getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalAmount;
    }

    public int getNumOfOrders() {
        return numOfOrders;
    }

    public double getTotalAmountByCustomerId(int customerId) {
        totalAmountByCustomerId(customerId);
        return totalAmountForOrder;
    }

    /// ////////////////////////////files/////////////////////////////////
    private void addToFile() throws FileNotFoundException {
        try {
            File file = new File("Order.txt");
            FileWriter fw = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fw);
            printWriter.println("OrderId is " + orderId);
            printWriter.println("CustomerId is " + customerId);
            for (OrderDetails orderedItem : items) {
                printWriter.println(orderedItem.getMealIdFromFile() + " " + orderedItem.getMealName() + " " + orderedItem.getQuantity());
            }
            printWriter.println("TotalAmount is " + totalAmount);
            printWriter.println("******************");
            printWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void deleteOrderFromFile(int targetOrderId) {
        File file = new File("Order.txt");
        File tempFile = new File("TempOrder.txt");

        try {
            Scanner scanner = new Scanner(file);
            PrintWriter printWriter = new PrintWriter(new FileWriter(tempFile));

            boolean skip = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("OrderId is")) {
                    int currentId = Integer.parseInt(line.replace("OrderId is", "").trim());
                    skip = (currentId == targetOrderId);
                }


                if (!skip) {
                    printWriter.println(line);
                }

                if (line.startsWith("******************")) {
                    skip = false;
                }
            }

            printWriter.close();
            scanner.close();

            if (file.delete()) {
                tempFile.renameTo(file);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Ahmed Ali
    public void totalAmountByCustomerId(int targetCustomerId) {
        File file = new File("Order.txt");

        double total = 0.0;
        boolean recording = false;
        boolean matchedCustomer = false;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // بداية Order جديد
                if (line.startsWith("OrderId is")) {
                    recording = true;
                    matchedCustomer = false;
                    continue;
                }

                // CustomerId
                if (line.startsWith("CustomerId is")) {
                    int custId = Integer.parseInt(line.replace("CustomerId is", "").trim());
                    if (custId == targetCustomerId) {
                        matchedCustomer = true;  // ده الأوردر اللي عايزين نحسبه
                    }
                }

                // TotalAmount
                if (matchedCustomer && line.startsWith("TotalAmount is")) {
                    double amount = Double.parseDouble(line.replace("TotalAmount is", "").trim());
                    total += amount;    // نجمع الفلوس
                }

                // نهاية Order
                if (line.startsWith("***************")) {
                    recording = false;
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        this.setTotalAmountByCustomerId(total);
    }

}

