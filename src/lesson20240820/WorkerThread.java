package lesson20240820;

import java.util.concurrent.Executor;

public class WorkerThread implements Executor {
    private final Thread thread = new Thread(this::process);
    private final BlockingQueue<Runnable> queue = new BlockingQueue<>();

    public WorkerThread() {
        thread.start();
    }

    @Override
    public void execute(Runnable task) {
        queue.put(task);
    }

    private void process() {
        while (true) {
            Runnable task = queue.get();
            task.run();
        }
    }
}
