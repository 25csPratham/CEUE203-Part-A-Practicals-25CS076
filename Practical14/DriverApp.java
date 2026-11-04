import java.util.Scanner;

public class DriverApp {
    public static void main(String[] args) {
        ItemService service = new ItemService();
        service.addItem(new Item(1, "Notebook", 45.0));
        service.addItem(new Item(2, "Pen Set", 120.0));

        System.out.println("=== Layered Inventory Application Menu ===");
        System.out.println("Current item count: " + service.getCount());
        for (Item item : service.getAll()) {
            System.out.println(" - " + item);
        }

        System.out.println("\nSearching for Item ID 2: " + service.findById(2));
        System.out.println("Searching for Item ID 99: " + service.findById(99));
    }
}