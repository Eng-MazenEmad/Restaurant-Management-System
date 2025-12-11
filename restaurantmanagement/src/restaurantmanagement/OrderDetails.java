package restaurantmanagement;

import java.io.*;

public class OrderDetails {
    private int mealId;
    private int quantity;
    private String mealName;
    private int mealPrice;

    public OrderDetails(String mealName, int quantity) {
        this.mealName = mealName;
        this.quantity = quantity;
        getPriceIdFromFile();
    }

    /// //////////////////////Methodes////////////////////////////
    public void getPriceIdFromFile() {
        int[] result = {-1, -1}; // {id, price}

        try (BufferedReader br = new BufferedReader(new FileReader("meals.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int price = Integer.parseInt(parts[2]);

                if (name.equalsIgnoreCase(mealName)) {
                    result[0] = id;
                    result[1] = price;
                    break;
                }
            }
        } catch (Exception e) {
            ;
        }
        this.mealId = result[0];
        this.mealPrice = result[1];

    }
    ///////////////////Setters//////////////////////////////////
    private void setMealId(int id) {
        this.mealId = id;
    }
    public void setMealPrice(int price) {
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
    public int getMealPrice() {
        return mealPrice;
    }
}
