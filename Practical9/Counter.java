public class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public synchronized void syncIncrement() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void reset() {
        count = 0;
    }
}
