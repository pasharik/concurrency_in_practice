package ru.pasharik.chapter0.scjp.example2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;

/**
 * Created by pasharik on 17/04/17.
 * Unbounded blocking queue
 */
@ThreadSafe
public class MyQueue<T> {
    @GuardedBy("this")
    private final LinkedList<T> list = new LinkedList<T>();

    public T take() throws InterruptedException {
        synchronized (list) {
            while (list.isEmpty()) list.wait();
            return list.removeFirst();
        }
    }

    public void put(T element) {
        synchronized (list) {
            list.add(element);
            list.notifyAll();
        }
    }
}
