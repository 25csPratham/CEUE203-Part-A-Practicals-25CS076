public class DriverArraySum {
    public static void main(String[] args) throws InterruptedException {
        int size = 1_000_000;
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = 1;
        }
        long expectedSum = size;
        int numThreads = 4;
        int chunkSize = size / numThreads;

        ArraySum sumTracker = new ArraySum();

        // 1. Unsynchronized Shared Total
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? size : start + chunkSize;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    sumTracker.addUnsafe(numbers[j]);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("1. Unsynchronized Sum: " + sumTracker.getTotal() + " (Expected: " + expectedSum + ")");

        // 2. Fix 1: Synchronized method
        sumTracker.reset();
        long startSync = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? size : start + chunkSize;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    sumTracker.addSync(numbers[j]);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long timeSync = System.currentTimeMillis() - startSync;
        System.out.println("2. Fix 1 (Synchronized): " + sumTracker.getTotal() + " in " + timeSync + " ms");

        // 3. Fix 2: Thread-local partial sums (Divide-and-Conquer)
        long startLocal = System.currentTimeMillis();
        long[] partialSums = new long[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int threadIdx = i;
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? size : start + chunkSize;
            threads[i] = new Thread(() -> {
                long localSum = 0;
                for (int j = start; j < end; j++) {
                    localSum += numbers[j];
                }
                partialSums[threadIdx] = localSum;
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long finalTotal = 0;
        for (long p : partialSums) {
            finalTotal += p;
        }
        long timeLocal = System.currentTimeMillis() - startLocal;
        System.out.println("3. Fix 2 (Thread-local Partial Sums): " + finalTotal + " in " + timeLocal + " ms");
        System.out.println("Performance Comparison: Thread-local computation is significantly faster due to zero lock contention!");
    }
}
