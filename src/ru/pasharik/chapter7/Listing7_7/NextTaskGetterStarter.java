package ru.pasharik.chapter7.Listing7_7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by pasharik on 16/01/18.
 */
public class NextTaskGetterStarter {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new NextTaskGetter(new LinkedBlockingQueue<String>());
        t.start();
        try {
            Thread.sleep(1000);
        } finally {
            t.interrupt();
        }
    }
}
