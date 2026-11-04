package app;

import model.Product;
import service.ProductService;
import storage.ProductStorage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  Modular Product Management Application (JAR)");
        System.out.println("=================================================");

        ProductService service = new ProductService();
        service.addProduct(new Product(101, "Mechanical Keyboard", 3499.0));
        service.addProduct(new Product(102, "Gaming Mouse", 1899.0));
        service.addProduct(new Product(103, "Monitor Arm", 2499.0));

        System.out.println("Products loaded in service:");
        for (Product p : service.getProducts()) {
            System.out.println(" - " + p);
        }

        Path dataFile = Paths.get("products_catalog.dat");
        try {
            ProductStorage.save(service.getProducts(), dataFile);
            System.out.println("\n[Storage] Catalog saved to: " + dataFile.getFileName());

            List<Product> reloaded = ProductStorage.load(dataFile);
            System.out.println("[Storage] Catalog successfully reloaded (" + reloaded.size() + " products).");
        } catch (Exception e) {
            System.err.println("Error during persistence: " + e.getMessage());
        }
        System.out.println("Application executed successfully from packaged JAR.");
    }
}