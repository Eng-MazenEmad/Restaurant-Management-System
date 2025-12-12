package Customer;

public class LoyaltySystem {

    private static final int pointsPer100 = 10;

    // Fully static method — no object needed
    public static void setLoyaltyPoints(Customer customer) {
        if (customer == null) {
            System.out.println("Unable to find customer.");
            return;
        }

        // Use Order class getter to get total spent
        Order order = new Order(); // dummy Order object to access method
        double totalSpent = order.getTotalAmountByCustomerId(customer.getCustID());

        // Calculate points: 10 points per 100 EGP
        int loyaltyPoints = ((int) (totalSpent / 100)) * pointsPer100;

        // Set points in the customer
        customer.setLoyaltyPoints(loyaltyPoints);
    }
}

