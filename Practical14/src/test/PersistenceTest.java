package test;

import model.Product;
import storage.ProductStorage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PersistenceTest {
    public static void main(String[] args) {
        System.out.println("=== Running Persistence Integration Test ===");
        Path testFile = Paths.get("test_products.dat");

        try {
            List<Product> original = List.of(
                new Product(1, "Laptop", 65000.0),
                new Product(2, "Desk Pad", 499.0)
            );

            // Save
            ProductStorage.save(original, testFile);

            // Reload
            List<Product> reloaded = ProductStorage.load(testFile);

            // Assert
            boolean matches = original.size() == reloaded.size();
            for (int i = 0; i < original.size(); i++) {
                if (!original.get(i).equals(reloaded.get(i))) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                System.out.println("[PASS] PersistenceTest: Saved and reloaded data matches perfectly unchanged!");
                System.out.println("Original: " + original);
                System.out.println("Reloaded: " + reloaded);
            } else {
                System.err.println("[FAIL] PersistenceTest: Data mismatch between original and reloaded");
            }

            Files.deleteIfExists(testFile);
        } catch (Exception e) {
            System.err.println("[FAIL] Exception during test: " + e.getMessage());
        }
    }
}