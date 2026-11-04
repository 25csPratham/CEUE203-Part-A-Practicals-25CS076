import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Buffer full (" + queue.size() + "/" + capacity + "). Producer waiting...");
            wait();
        }
        queue.add(item);
        System.out.println("Produced: " + item + " | Buffer size: " + queue.size());
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer empty. Consumer waiting...");
            wait();
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item + " | Buffer size: " + queue.size());
        notifyAll();
        return item;
    }
}