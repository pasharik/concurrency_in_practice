package ru.pasharik.chapter3.Listing3_3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by pasharik on 31/03/17.
 */
@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy("this") private int value;

    public synchronized int getValue() { return value; }
    public synchronized void setValue(int value) { this.value = value; }
}
