package ru.pasharik.chapter5.Listing5_8;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pasharik on 15/04/17.
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter filter;
    private final File root;

    public FileCrawler(BlockingQueue<File> queue, FileFilter filter, File root) {
        this.fileQueue = queue;
        this.filter = filter;
        this.root = root;
    }

    public void run() {
        try { crawl(root); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
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
