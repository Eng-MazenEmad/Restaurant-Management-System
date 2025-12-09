import java.util.*;
        import java.io.*;

public class MealManager {
    private static final String  FILE_PATH = "meals.txt";
    public static List<Meal> readMeals() {
        List<Meal> meals = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    meals.add(new Meal(id, name, price));
                } catch (NumberFormatException nfe) {

                }
            }
        } catch (Exception e) {
         //ههاندل الايرورز مع ال GUI stay tuned
        }
        return meals;
    }
}