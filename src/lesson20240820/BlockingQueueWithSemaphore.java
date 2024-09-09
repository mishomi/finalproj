package lesson20240820;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BlockingQueueWithSemaphore<T> {

    private final Queue<T> items = new LinkedList<>();
    private final Semaphore itemsAvailable = new Semaphore(10);
    private final Semaphore spacesAvailable;

    public BlockingQueueWithSemaphore(int capacity) {
        spacesAvailable = new Semaphore(capacity);
    }

    public void put(T item) throws InterruptedException {
        spacesAvailable.acquire();
        synchronized (this) {
            items.add(item);
        }
        itemsAvailable.release();
    }

    public T get() throws InterruptedException {
        itemsAvailable.acquire();
        T item;
        synchronized (this) {
            item = items.poll();
        }
        spacesAvailable.release();
        return item;
    }
}
