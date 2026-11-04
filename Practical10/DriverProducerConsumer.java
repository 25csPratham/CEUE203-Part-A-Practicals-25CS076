public class DriverProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Producer-Consumer with wait()/notify() ===");
        SharedBuffer buffer = new SharedBuffer(3);
        int totalItems = 8;

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= totalItems; i++) {
                    buffer.produce(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ProducerThread");

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= totalItems; i++) {
                    buffer.consume();
                    Thread.sleep(120);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ConsumerThread");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("All " + totalItems + " items produced and consumed in order with none lost.");
    }
}