//الكلاس الضارب المشطشط 🌶️🌶️🌶️🌶️
import java.util.ArrayList;
public class Employee  {

 
    ArrayList<Customer> customers = new ArrayList<>();

   

    public boolean addCustomer(Customer customer) {
        for (Customer cust : customers) {
            if (cust.getCustID() == customer.getCustID()) {
                System.out.println("Error: Customer ID already exists!");
                return false;
            }
        }
        customers.add(customer);
        System.out.println("Employee added: " + customer.getCustName());
        return true;
    }

    public boolean deleteCustomer(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustID() == id) {
                customers.remove(i);
                return true;
            }
        }
        System.out.println("Don't exist");
        return false;
    }

    public boolean updateCustmer(int id, String newCustname, String phoneNum, String address) {
        for (Customer c : customers) {
            if (c.getCustID() == id) {
                c.setCustName(newCustname);
                c.setPhoneNum(phoneNum);
                c.setAddress(address);
                System.out.println("Employee updated: ");
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> listCustomer() {
        ArrayList<String> CustomerInfo = new ArrayList<>();
        for (Customer c : customers) {
            CustomerInfo.add("Username: " + c.getCustName() + "  " + "ID: " + c.getCustID());
        }
        return CustomerInfo;
    }

    public String searchEmployee(int id) {
        for (Customer c : customers) {
            if (id == c.getCustID()) {
                return c.getCustName();
            }
        }
        System.out.println("Don't exist");
        return null;
    }

    public boolean manageOrder(int orderId, Meal item, int choice) {
        Order ord = new Order(orderId);
        if (choice == 1) {
            ord.makeOrder(item);
            System.out.println("Operation successful!");
            return true;
        }
        if (choice == 0) {
            ord.cancelOrder();
            System.out.println("order canceled!");
            return true;
        }
        return false;
    }

}
