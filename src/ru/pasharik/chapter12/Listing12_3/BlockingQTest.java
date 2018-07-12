package ru.pasharik.chapter12.Listing12_3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pasharik on 12/07/18.
 */
public class BlockingQTest {
    public static void main(String[] args) {
        BlockingQueue q = new ArrayBlockingQueue(10);
        Thread testT = new Thread() {
            @Override
            public void run() {
                try {
                    q.take();
                    fail(); //We should not get here
                } catch (InterruptedException e) { /*success*/ }
            }
        };
        try {
            testT.start();
            testT.interrupt();
            testT.join(1000);
            if (testT.isAlive()) fail();
        } catch (Exception unexpected) {
            fail();
        }
    }

    public static void fail() { throw new RuntimeException("Test failed"); }
}
