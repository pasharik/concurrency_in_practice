package ru.pasharik.chapter7.Listing7_21;

import java.util.concurrent.TimeUnit;

/**
 * Created by pasharik on 24/02/18.
 */
public class TrackingExecutorStarter {
    public static void main(String[] args) throws InterruptedException {
        new TrackingExecutorStarter().startExecutor();
    }

    private void startExecutor() throws InterruptedException {
        TrackingExecutor exec = new TrackingExecutor();
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000* finalI);
                    } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                }
            });
        }

        exec.shutdown();
        System.out.println("Waiting 2 seconds for tasks to finish...");
        Thread.sleep(2000);
        System.out.println("Cancelling all tasks...");
        exec.shutdownNow();
        while (!exec.awaitTermination(500, TimeUnit.MILLISECONDS));
        System.out.println("Interrupted tasks:");
        System.out.println(exec.getCancelledTasks());
    }
}
