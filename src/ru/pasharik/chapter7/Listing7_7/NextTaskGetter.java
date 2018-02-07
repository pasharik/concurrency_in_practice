package ru.pasharik.chapter7.Listing7_7;

import java.util.concurrent.BlockingQueue;

/**
 * Created by pasharik on 16/01/18.
 */
public class NextTaskGetter extends Thread {
    BlockingQueue<String> queue;

    public NextTaskGetter(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String task = getNextTask(queue);
    }

    private String getNextTask(BlockingQueue<String> q) {
        boolean cancelled = false;
        try {
            while (true) {
                try {
                    return q.take();
                } catch (InterruptedException e) {
                    cancelled = true;
                    System.out.println("Exception!!!111");
                }
            }
        } finally {
            if (cancelled) Thread.currentThread().interrupt();
        }
    }
}
