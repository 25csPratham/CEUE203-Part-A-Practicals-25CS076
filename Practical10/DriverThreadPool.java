import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DriverThreadPool {
    public static void main(String[] args) {
        System.out.println("=== Fixed Thread Pool (Size: 3) Running 10 Tasks ===");
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            executor.submit(new WorkerTask(i));
        }

        executor.shutdown();
        try {
            if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("All 10 tasks completed successfully.");
                System.out.println("Observation: Only 3 worker threads were reused to execute all 10 tasks.");
            }
        } catch (InterruptedException e) {
            System.err.println("Execution interrupted.");
        }
    }
}