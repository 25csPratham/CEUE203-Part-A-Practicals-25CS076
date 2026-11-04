public class WorkerTask implements Runnable {
    private final int taskId;

    public WorkerTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Task " + taskId + " started by [" + threadName + "]");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Task " + taskId + " completed by [" + threadName + "]");
    }
}