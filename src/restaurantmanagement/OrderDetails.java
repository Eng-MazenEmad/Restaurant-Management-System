package restaurantmanagement;

import java.io.*;

public class OrderDetails {
    private int mealId;
    private int quantity;
    private String mealName;
    private double mealPrice; // 1️⃣ غيرت int -> double

    public OrderDetails(String mealName, int quantity) {
        this.mealName = mealName;
        this.quantity = quantity;
        getPriceIdFromFile(); // 2️⃣ نفس الطريقة بدون براميترز
    }

    /// //////////////////////Methodes////////////////////////////
    public void getPriceIdFromFile() {
        this.mealId = -1;
        this.mealPrice = -1;

        try (BufferedReader br = new BufferedReader(new FileReader("meals.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim()); // 3️⃣ بدل int -> double

                if (name.equalsIgnoreCase(mealName.trim())) { // 4️⃣ trim + ignoreCase
                    this.mealId = id;
                    this.mealPrice = price;
                    break;
                }
            }

            if (this.mealId == -1) {
                System.out.println("Warning: Meal name not found in file!");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: meals.txt file not found!");
        } catch (Exception e) {
            e.printStackTrace(); // 5️⃣ اطبع أي خطأ
        }
    }

    ///////////////////Setters//////////////////////////////////
    private void setMealId(int id) {
        this.mealId = id;
    }
    public void setMealPrice(double price) { // 6️⃣ عدلت Setter
        this.mealPrice = price;
    }

    /// //////////////////////Getters////////////////////////////
    public int getMealIdFromFile() {
        return mealId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMealName() {
        return mealName;
    }
    public double getMealPrice() { // 7️⃣ عدلت Getter
        return mealPrice;
    }
}
