public class ArraySum {
    private long total = 0;

    public void addUnsafe(long val) {
        total += val;
    }

    public synchronized void addSync(long val) {
        total += val;
    }

    public long getTotal() {
        return total;
    }

    public void reset() {
        total = 0;
    }
}
