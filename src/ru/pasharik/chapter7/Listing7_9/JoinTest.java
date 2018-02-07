package ru.pasharik.chapter7.Listing7_9;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by pasharik on 18/01/18.
 */
public class JoinTest {
    private static class WaitingQ implements Runnable {
        @Override
        public void run() {
            try {
                new LinkedBlockingDeque<>().take();
            } catch (InterruptedException e) {
                System.out.println("Interrupted!!!111");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new WaitingQ());
        t.start();
        System.out.println("Joining...");
        t.join(1000);
        System.out.println("Exiting...");
    }
}
