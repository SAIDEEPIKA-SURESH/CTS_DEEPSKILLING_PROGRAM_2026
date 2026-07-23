package exercise1;

import java.util.HashMap;

public class InventoryManagement {

    static HashMap<Integer, Product> inventory = new HashMap<>();

    public static void addProduct(Product p) {
        inventory.put(p.productId, p);
    }

    public static void updateProduct(int id, int quantity) {
        if (inventory.containsKey(id)) {
            inventory.get(id).quantity = quantity;
        }
    }

    public static void deleteProduct(int id) {
        inventory.remove(id);
    }

    public static void display() {
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        addProduct(new Product(101, "Laptop", 10, 50000));
        addProduct(new Product(102, "Mouse", 50, 500));

        System.out.println("Initial Inventory:");
        display();

        updateProduct(101, 15);
        deleteProduct(102);

        System.out.println("\nAfter Update/Delete:");
        display();
    }
}
