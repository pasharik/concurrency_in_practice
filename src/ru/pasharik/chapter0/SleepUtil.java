package ru.pasharik.chapter0;

/**
 * Created by pasharik on 24/07/18.
 */
public class SleepUtil {
    public static void doSleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
