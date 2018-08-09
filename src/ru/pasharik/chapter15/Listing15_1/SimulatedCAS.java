package ru.pasharik.chapter15.Listing15_1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by pasharik on 09/08/18.
 */
@ThreadSafe
public class SimulatedCAS {
    @GuardedBy("this")
    private int value;

    public SimulatedCAS(int val) { this.value = val; }

    public synchronized int getValue() { return value; }

    public synchronized int compareAndSwap(int expected, int newVal) {
        int old = value;
        if (value == expected)
            value = newVal;
        return old;
    }

    public synchronized boolean compareAndSet(int expected, int newVal) {
        return (expected == compareAndSwap(expected, newVal));
    }
}
