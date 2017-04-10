package ru.pasharik.chapter4.Listing4_3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by pasharik on 10/04/17.
 */
@ThreadSafe
public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock")
    private int value = 0;

    public void increment() {
        synchronized (myLock) {
            value++;
        }
    }

    public int get() {
        synchronized (myLock) {
            return value;
        }
    }
}
