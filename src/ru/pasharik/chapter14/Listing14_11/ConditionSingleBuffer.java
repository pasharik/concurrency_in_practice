package ru.pasharik.chapter14.Listing14_11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pasharik on 31/07/18.
 */
public class ConditionSingleBuffer {
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private Object buffer = null;

    public synchronized void put(Object obj) throws InterruptedException {
        lock.lock();
        try {
            if (buffer != null)
                notEmpty.await();
            buffer = obj;
            notFull.signal();
        } finally { lock.unlock(); }
    }

    public synchronized Object get() throws InterruptedException {
        lock.lock();
        try {
            if (buffer == null)
                notFull.await();
            Object tmp = buffer;
            buffer = null;
            notEmpty.signal();
            return tmp;
        } finally { lock.unlock(); }

    }

    public static void main(String[] args) throws InterruptedException {
        ConditionSingleBuffer buf = new ConditionSingleBuffer();
        buf.put("Hello, world!");
        System.out.println(buf.get());
    }
}
