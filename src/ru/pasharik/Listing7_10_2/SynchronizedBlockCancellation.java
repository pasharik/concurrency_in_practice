package ru.pasharik.Listing7_10_2;

/**
 * Created by pasharik on 15/02/18.
 * If Thread is waiting on intrinsic (synchronized) lock, it doesn't respond to interrupt()
 */
public class SynchronizedBlockCancellation {
    private static synchronized void doWait() {
        System.out.println("Thread: " + Thread.currentThread().getName() + " entered");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    private static class TestRun implements Runnable {
        @Override
        public void run() {
            doWait();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new TestRun(), "T1");
        Thread t2 = new Thread(new TestRun(), "T2");
        t1.start();
        Thread.sleep(100);
        t2.start();
        System.out.println("T2 started");
        t2.interrupt();
        System.out.println("Trying to interrupt T2...");
    }
}
