package ru.pasharik.chapter7.Listing7_15;

import net.jcip.annotations.GuardedBy;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by pasharik on 17/02/18.
 */
public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    @GuardedBy("this")
    private boolean isShutdown;
    @GuardedBy("this")
    private int count;

    public void start() { logger.start(); }

    public void stop() {
        synchronized (this) { isShutdown = true; }
        logger.interrupt(); //In case Q is already empty
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) throw new IllegalStateException("Logger is shut down");
            ++count;
        }
        queue.put(msg);
    }

    public LogService(PrintWriter writer) {
        queue = new LinkedBlockingQueue<>(100);
        logger = new LoggerThread(writer);
    }

    private class LoggerThread extends Thread {
        private final PrintWriter w;

        public LoggerThread(PrintWriter w) {
            this.w = w;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (this) { if (isShutdown && count == 0) break; }
                        String msg = queue.take();
                        synchronized (this) { --count; }
                        w.println(msg);
                        Thread.sleep(1000); //For test only
                    } catch (InterruptedException ignored) { /* exit only when Q is empty */ }
                }
            } finally { w.close(); }
        }
    }
}
