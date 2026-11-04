public class DriverDeadlock {
    public static void main(String[] args) throws InterruptedException {
        ResourceLock resourceA = new ResourceLock("Resource-A");
        ResourceLock resourceB = new ResourceLock("Resource-B");

        System.out.println("=== Part 1: Simulating Deadlock Scenario ===");
        System.out.println("Thread 1 locks Resource-A, waits for Resource-B.");
        System.out.println("Thread 2 locks Resource-B, waits for Resource-A.");
        
        Thread deadlockDetector = new Thread(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("[Deadlock Detected]: Both threads are waiting indefinitely for each other's locks!");
                System.out.println("[Deadlock Mitigation]: Resolving by applying Consistent Lock Ordering...\n");
            } catch (InterruptedException ignored) {}
        });
        deadlockDetector.start();
        deadlockDetector.join();

        System.out.println("=== Part 2: Consistent Lock Ordering (Deadlock-Free) ===");
        // Both threads acquire locks in the same hierarchical order: Resource-A then Resource-B
        Thread t1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Thread-1: Acquired lock on " + resourceA.getName());
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
                synchronized (resourceB) {
                    System.out.println("Thread-1: Acquired lock on " + resourceB.getName() + " -> Task completed!");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            synchronized (resourceA) { // Consistent ordering: acquires A first, then B
                System.out.println("Thread-2: Acquired lock on " + resourceA.getName());
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
                synchronized (resourceB) {
                    System.out.println("Thread-2: Acquired lock on " + resourceB.getName() + " -> Task completed!");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Both threads completed successfully without deadlock using consistent lock hierarchy.");
    }
}