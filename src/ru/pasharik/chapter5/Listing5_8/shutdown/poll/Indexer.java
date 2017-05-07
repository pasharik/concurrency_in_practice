package ru.pasharik.chapter5.Listing5_8.shutdown.poll;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pasharik on 15/04/17.
 */
public class Indexer implements Runnable {
    private final BlockingQueue<File> queue;
    private final AtomicInteger numCrawlers;

    public Indexer(BlockingQueue<File> queue, AtomicInteger numCrawlers) {
        this.queue = queue;
        this.numCrawlers = numCrawlers;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(200);
                File file = queue.poll(1, TimeUnit.SECONDS);
                if (file != null) {
                    //System.out.println(queue.size());
                    indexFile(file);
                } else if (numCrawlers.get() == 0 && queue.isEmpty()) {
                    break;
                }
            }
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    private void indexFile(File file) {
        System.out.println("Q size: " + queue.size() + " numCrawlers: " + numCrawlers.get() + " | " + file.getName());
    }
}
