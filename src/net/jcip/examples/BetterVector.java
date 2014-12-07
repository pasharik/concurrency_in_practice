package net.jcip.examples;

import net.jcip.annotations.ThreadSafe;

import java.util.Vector;

/**
 * @author Pavel Pozdeev
 * @since 02.12.14
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent)
            add(x);
        return absent;
    }
}
