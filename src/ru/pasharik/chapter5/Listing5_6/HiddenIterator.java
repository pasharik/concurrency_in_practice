package ru.pasharik.chapter5.Listing5_6;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by pasharik on 13/04/17.
 */
@NotThreadSafe
public class HiddenIterator {
    private static final int AMOUNT = 1000;

    @GuardedBy("this")
    private static final Set<Integer> set = new HashSet<>();

    //Uncomment to fix ConcurrentModificationException
    //private static final Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    public static synchronized void add(Integer i) { set.add(i); }
    public static synchronized void remove(Integer i) { set.remove(i); }

    private static class ThreadAdd extends Thread {
        public void run() {
            for (int i = 0; i < AMOUNT; i++) add(1);
        }
    }

    private static class ThreadRemove extends Thread {
        public void run() {
            for (int i = 0; i < AMOUNT; i++) remove(1);
        }
    }

    private static class ThreadToString extends Thread {
        public void run() {
            for (int i = 0; i < AMOUNT; i++) addTenThings();
        }
    }

    public static void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) add(r.nextInt());
        //synchronized (HiddenIterator.class) { //Uncomment to fix ConcurrentModificationException
            String str = "Added ten elements to " + set;
        //}
    }

    public static void main(String[] args) {
        new ThreadAdd().start();
        new ThreadToString().start();
        new ThreadRemove().start();
    }
}
