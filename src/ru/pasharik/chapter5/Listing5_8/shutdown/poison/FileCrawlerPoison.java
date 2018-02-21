package ru.pasharik.chapter5.Listing5_8.shutdown.poison;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pasharik on 15/04/17.
 */
public class FileCrawlerPoison extends Thread {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter filter;
    private final File root;
    private final int numConsumers;
    public static final File POISON_PILL = new File("");

    public FileCrawlerPoison(BlockingQueue<File> queue, FileFilter filter, File root, int numConsumers) {
        this.fileQueue = queue;
        this.filter = filter;
        this.root = root;
        this.numConsumers = numConsumers;
    }

    public void run() {
        try {
            crawl(root);
        }
        catch (InterruptedException consumed) { /*Fall through*/ }
        finally {
            int pillsLeft = numConsumers;
            while (pillsLeft > 0) {
                try {
                    fileQueue.put(POISON_PILL);
                    pillsLeft--;
                } catch (InterruptedException consumed) { /* Retry */ }
            }
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(filter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) crawl(entry);
                else fileQueue.put(entry);
            }
        }
    }
}
