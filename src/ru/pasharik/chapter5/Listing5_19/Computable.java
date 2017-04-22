package ru.pasharik.chapter5.Listing5_19;

/**
 * Created by pasharik on 22/04/17.
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
