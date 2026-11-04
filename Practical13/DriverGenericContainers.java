public class DriverGenericContainers {
    // Generic method returning the first element of any array
    public static <T> T getFirst(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Generic Box<T> ===");
        Box<String> stringBox = new Box<>("Java Generics Demonstration");
        Box<Integer> intBox = new Box<>(2026);

        // Type safety: No casts required
        String strVal = stringBox.get();
        int intVal = intBox.get();
        System.out.println("String Box contains: " + strVal);
        System.out.println("Integer Box contains: " + intVal);

        System.out.println("\n=== 2. Generic Pair<A, B> ===");
        Pair<String, Double> productPrice = new Pair<>("MacBook Pro", 199999.00);
        Pair<Integer, String> studentRoll = new Pair<>(76, "Pratham Patel");

        System.out.println("Product: " + productPrice.getFirst() + " | Price: Rs. " + productPrice.getSecond());
        System.out.println("Roll No: " + studentRoll.getFirst() + " | Name: " + studentRoll.getSecond());

        System.out.println("\n=== 3. Generic Method getFirst() ===");
        String[] fruits = {"Apple", "Banana", "Cherry"};
        Integer[] numbers = {10, 20, 30, 40};
        Double[] decimals = {3.14, 2.718, 1.414};

        System.out.println("First fruit  : " + getFirst(fruits));
        System.out.println("First number : " + getFirst(numbers));
        System.out.println("First decimal: " + getFirst(decimals));
    }
}