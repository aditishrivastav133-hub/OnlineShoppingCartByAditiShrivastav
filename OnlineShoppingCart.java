import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class OnlineShoppingCart {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> items = new ArrayList<>();
    private static final HashMap<String, Double> prices = new HashMap<>();
    private static final HashMap<String, Integer> cart = new HashMap<>();

    public static void main(String[] args) {
        loadStoreItems();

        System.out.println("=================================");
        System.out.println(" Welcome to Online Shopping Cart ");
        System.out.println("=================================");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    viewAvailableItems();
                    break;
                case 2:
                    addItemToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    calculateTotal();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Please choose a valid option from 1 to 5.");
            }
        }

        scanner.close();
    }
 private static void loadStoreItems() {
        addStoreItem("Laptop", 55000.00);
        addStoreItem("Headphones", 1999.00);
        addStoreItem("Keyboard", 1499.00);
        addStoreItem("Mouse", 799.00);
        addStoreItem("USB Cable", 299.00);
    }

    private static void addStoreItem(String itemName, double price) {
        items.add(itemName);
        prices.put(itemName, price);
    }
     private static void showMenu() {
        System.out.println();
        System.out.println("1. View available items");
        System.out.println("2. Add item to cart");
        System.out.println("3. View cart");
        System.out.println("4. Calculate total price");
        System.out.println("5. Exit");
    }

    private static void viewAvailableItems() {
        System.out.println();
        System.out.println("Available Items:");
        for (int i = 0; i < items.size(); i++) {
            String itemName = items.get(i);
            System.out.printf("%d. %s - Rs. %.2f%n", i + 1, itemName, prices.get(itemName));
        }
    }
    private static void addItemToCart() {
        viewAvailableItems();

        int itemNumber = readInt("Enter item number to add: ");
        if (itemNumber < 1 || itemNumber > items.size()) {
            System.out.println("Invalid item number. Please try again.");
            return;
        }

        int quantity = readInt("Enter quantity: ");
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        String selectedItem = items.get(itemNumber - 1);
        int updatedQuantity = cart.getOrDefault(selectedItem, 0) + quantity;
        cart.put(selectedItem, updatedQuantity);

        System.out.println(quantity + " x " + selectedItem + " added to your cart.");
    }
      private static void viewCart() {
        System.out.println();
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Your Cart:");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double itemPrice = prices.get(itemName);
            double subtotal = itemPrice * quantity;

            System.out.printf("%s | Qty: %d | Price: Rs. %.2f | Subtotal: Rs. %.2f%n",
                    itemName, quantity, itemPrice, subtotal);
        }
    }
    private static void calculateTotal() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Total price: Rs. 0.00");
            return;
        }

        double total = 0.0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            total += prices.get(itemName) * entry.getValue();
        }

        System.out.printf("Total price: Rs. %.2f%n", total);
    }   
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException exception) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}