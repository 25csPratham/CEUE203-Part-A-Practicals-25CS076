public class DriverBoundedStats {
    // Generic method with bounded type parameter <N extends Number>
    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (N val : values) {
            sum += val.doubleValue(); // Safe because N is guaranteed to be a subtype of Number
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        System.out.println("=== Generic Bounded Method: <N extends Number> double average(N[] values) ===");

        Integer[] intValues = {10, 25, 30, 45, 50};
        Double[] doubleValues = {12.5, 23.4, 34.6, 45.8};
        Long[] longValues = {100000L, 250000L, 400000L};

        System.out.printf("Average of Integer[] : %.2f\n", average(intValues));
        System.out.printf("Average of Double[]  : %.2f\n", average(doubleValues));
        System.out.printf("Average of Long[]    : %.2f\n", average(longValues));

        System.out.println("\nCompiler Constraint Explanation:");
        System.out.println("Attempting to pass non-Number types (e.g. String[] names = {\"A\", \"B\"})");
        System.out.println("results in a compile-time error: 'type argument String is not within bounds of type-variable N'.");
        System.out.println("This guarantees compile-time type safety and prevents ClassCastException at runtime.");
    }
}