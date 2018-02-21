package ru.pasharik.chapter5.Listing5_8.shutdown.poison;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pasharik on 15/04/17.
 */
public class IndexerPoison implements Runnable {
    private final BlockingQueue<File> queue;
    private int numProducers;

    public IndexerPoison(BlockingQueue<File> queue, int numProducers) {
        this.queue = queue;
        this.numProducers = numProducers;
    }

    public void run() {
        try {
            while (numProducers > 0)
                indexFile(queue.take());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    private void indexFile(File file) {
        if (FileCrawlerPoison.POISON_PILL == file) {
            numProducers--;
        } else {
            System.out.println(file.getName());
        }
    }
}
