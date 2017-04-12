package ru.pasharik.chapter4.Listing4_14;

import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pasharik on 12/04/17.
 */
@NotThreadSafe
public class BrokenListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) list.add(x);
        return absent;
    }
}
