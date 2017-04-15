package ru.pasharik.chapter5.Listing5_8;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pasharik on 15/04/17.
 */
public class Indexer implements Runnable {
    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true)
                indexFile(queue.take());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    private void indexFile(File file) {
        System.out.println(file.getName());
    }
}
