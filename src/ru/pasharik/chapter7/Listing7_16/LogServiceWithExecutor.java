package ru.pasharik.chapter7.Listing7_16;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by pasharik on 20/02/18.
 */
public class LogServiceWithExecutor {
    private final ExecutorService executor = Executors.newFixedThreadPool(1);
    private PrintWriter writer;

    public LogServiceWithExecutor(PrintWriter w) {
        this.writer = w;
    }

    private class WriteTask implements Runnable {
        private String msg;
        public WriteTask(String msg) { this.msg = msg; }
        @Override
        public void run() { writer.println(msg); }
    }

    public void log(String msg) {
        try {
            executor.submit(new WriteTask(msg));
        } catch (RejectedExecutionException ignored) { /*May be re-throw to show what logger is shut down*/ }
    }

    public void stop() throws InterruptedException {
        try {
            executor.shutdown();
            executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
        } finally {
            writer.close();
        }
    }
}
