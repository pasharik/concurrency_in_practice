package ru.pasharik.chapter5.Listing5_8;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by pasharik on 15/04/17.
 */
public class DesktopSearch {
    private static final int BOUND = 100;
    private static final int N_CONSUMERS = 3;

    public static void main(String[] args) {
        File f = new File("/home/pasharik/Pictures"); //Warning: security incident!
        startIndexing(new File[]{f});
    }

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter filter = new FileFilter() { public boolean accept(File pathname) { return true; } };

        for (File root : roots)
            new Thread(new FileCrawler(queue, filter, root)).start();
        for (int i = 0; i < N_CONSUMERS; i++)
            new Thread(new Indexer(queue)).start();
    }
}
