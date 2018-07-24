package ru.pasharik.chapter14.Listing14_6;

import static ru.pasharik.chapter0.SleepUtil.doSleep;

/**
 * Created by pasharik on 24/07/18.
 */
public class SingleBufferTest {
    private SingleBuffer b;

    private void doTest() {
        b = new SingleBuffer();
        new Thread(() -> {
            try {
                for (int i = 0; i <= 10; i++) {
                    doSleep(500);
                    b.put(i);
                    System.out.println("Written: " + i);
                }
            } catch (InterruptedException e) { throw new RuntimeException(e); }
        }).start();
        new Thread(() -> {
            try {
                Integer res = null;
                do {
                    res = (Integer) b.get();
                    System.out.println("Read: " + res);
                } while (res < 10); //Poison pill
            } catch (InterruptedException e) { throw new RuntimeException(e); }
        }).start();
    }

    public static void main(String[] args) {
        new SingleBufferTest().doTest();
    }
}
