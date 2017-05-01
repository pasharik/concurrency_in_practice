package ru.pasharik.chapter6.Listing6_9;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by pasharik on 01/05/17.
 */
public class DelayQueueExample {
    private static final DelayQueue<DelayElement> q = new DelayQueue<>();
    private static final AtomicBoolean stopped = new AtomicBoolean(false);

    private static class QPoll extends Thread {
        public void run() {
            while (!stopped.get() || !q.isEmpty()) {
                try {
                    System.out.println(q.take());
                } catch (InterruptedException ignored) {
                    throw new RuntimeException(ignored);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new QPoll().start();
        for (int i = 0; i < 10; i++) {
            q.put(new DelayElement());
            TimeUnit.MILLISECONDS.sleep(400);
        }
        stopped.set(true);
    }

    private static class DelayElement implements Delayed {
        private long duration = System.currentTimeMillis() + 1000;

        public long getDelay(TimeUnit unit) {
            return duration - System.currentTimeMillis();
        }
        public int compareTo(Delayed o) {
            return (int) (this.duration - ((DelayElement) o).duration);
        }
    }
}
