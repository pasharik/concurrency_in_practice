package ru.pasharik.chapter4.Listing4_13;

import net.jcip.annotations.ThreadSafe;

import java.util.Vector;

/**
 * Created by pasharik on 11/04/17.
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) add(x);
        return absent;
    }
}
