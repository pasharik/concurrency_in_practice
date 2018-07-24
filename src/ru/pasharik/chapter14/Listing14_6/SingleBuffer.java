package ru.pasharik.chapter14.Listing14_6;

/**
 * Created by pasharik on 24/07/18.
 */
public class SingleBuffer {
    private Object buffer = null;

    public synchronized void put(Object obj) throws InterruptedException {
        while (buffer != null) {
            wait(); }
        buffer = obj;
        notifyAll();
    }

    public synchronized Object get() throws InterruptedException {
        while (buffer == null) {
            wait(); }
        Object o = buffer;
        buffer = null;
        notifyAll();
        return o;
    }
}
