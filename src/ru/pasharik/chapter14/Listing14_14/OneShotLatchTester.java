package ru.pasharik.chapter14.Listing14_14;

/**
 * Created by pasharik on 01/08/18.
 */
public class OneShotLatchTester {
    private final OneShotLatch latch = new OneShotLatch();

    private void doTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("Waiting...");
                try { latch.await(); } catch (InterruptedException e) { throw new RuntimeException(e); }
                System.out.println("Released!");
            }).start();
        }
        Thread.sleep(1000);
        latch.signal();
    }

    public static void main(String[] args) throws InterruptedException { new OneShotLatchTester().doTest(); }
}
