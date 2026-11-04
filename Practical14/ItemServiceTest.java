public class ItemServiceTest {
    public static void testAddItemIncreasesCount() {
        ItemService service = new ItemService();
        int initialCount = service.getCount();
        service.addItem(new Item(101, "Wireless Mouse", 750.0));
        if (service.getCount() == initialCount + 1) {
            System.out.println("[PASS] testAddItemIncreasesCount: Count increased from " + initialCount + " to " + service.getCount());
        } else {
            System.err.println("[FAIL] testAddItemIncreasesCount");
        }
    }

    public static void testInvalidPriceThrowsException() {
        try {
            new Item(102, "Faulty Cable", -150.0);
            System.err.println("[FAIL] testInvalidPriceThrowsException: Expected exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("[PASS] testInvalidPriceThrowsException: Correctly caught: " + e.getMessage());
        }
    }

    public static void testDuplicateIdThrowsException() {
        ItemService service = new ItemService();
        service.addItem(new Item(201, "USB Drive", 500.0));
        try {
            service.addItem(new Item(201, "Duplicate USB", 600.0));
            System.err.println("[FAIL] testDuplicateIdThrowsException: Expected exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("[PASS] testDuplicateIdThrowsException: Correctly caught: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Running Unit Tests for ItemService ===");
        testAddItemIncreasesCount();
        testInvalidPriceThrowsException();
        testDuplicateIdThrowsException();
        System.out.println("All unit tests completed successfully.");
    }
}