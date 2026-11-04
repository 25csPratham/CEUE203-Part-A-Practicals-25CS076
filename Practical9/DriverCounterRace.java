public class DriverCounterRace {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        int numThreads = 5;
        int incrementsPerThread = 10000;
        int expectedTotal = numThreads * incrementsPerThread;

        System.out.println("=== Unsynchronized Counter Run ===");
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Expected count: " + expectedTotal);
        System.out.println("Actual count  : " + counter.getCount());
        if (counter.getCount() < expectedTotal) {
            System.out.println("Race condition detected: count came out too low!");
        }

        System.out.println("\n=== Synchronized Counter Run ===");
        counter.reset();
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.syncIncrement();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Expected count: " + expectedTotal);
        System.out.println("Actual count  : " + counter.getCount());
        if (counter.getCount() == expectedTotal) {
            System.out.println("Synchronization successful: count is exactly right!");
        }
    }
}
