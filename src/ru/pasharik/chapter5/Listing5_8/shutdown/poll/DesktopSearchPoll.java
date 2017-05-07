package ru.pasharik.chapter5.Listing5_8.shutdown.poll;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pasharik on 15/04/17.
 */
public class DesktopSearchPoll {
    private static final int BOUND = 100;
    private static final int N_CONSUMERS = 3;
    public static final AtomicInteger numCrawlers = new AtomicInteger(0);

    public static void main(String[] args) {
        File f = new File("/home/pasharik/Anki"); //Warning: security incident!
        File f2 = new File("/home/pasharik/Downloads"); //Warning: security incident!
        startIndexing(new File[]{f, f2});
    }

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter filter = new FileFilter() { public boolean accept(File pathname) { return true; } };

        numCrawlers.set(roots.length);
        for (File root : roots)
            new Thread(new FileCrawler(queue, filter, root, numCrawlers)).start();
        for (int i = 0; i < N_CONSUMERS; i++)
            new Thread(new Indexer(queue, numCrawlers)).start();
    }
}
