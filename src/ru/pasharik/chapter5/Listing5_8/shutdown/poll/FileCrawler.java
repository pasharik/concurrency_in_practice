package ru.pasharik.chapter5.Listing5_8.shutdown.poll;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pasharik on 15/04/17.
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter filter;
    private final File root;
    private final AtomicInteger numCrawlers;

    public FileCrawler(BlockingQueue<File> queue, FileFilter filter, File root, AtomicInteger numCrawlers) {
        this.fileQueue = queue;
        this.filter = filter;
        this.root = root;
        this.numCrawlers = numCrawlers;
    }

    public void run() {
        try { crawl(root); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        finally {
            numCrawlers.decrementAndGet();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(filter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) crawl(entry);
                else fileQueue.put(entry);
                Thread.sleep(50);
            }
        }
    }
}
