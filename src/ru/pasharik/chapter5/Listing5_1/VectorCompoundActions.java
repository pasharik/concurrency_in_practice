package ru.pasharik.chapter5.Listing5_1;

import net.jcip.annotations.NotThreadSafe;

import java.util.Vector;

/**
 * Created by pasharik on 13/04/17.
 */
@NotThreadSafe
public class VectorCompoundActions {
    private static final int CAPACITY = 100_000;

    private static class ThreadGet extends Thread {
        private Vector list;
        public ThreadGet(Vector v) { this.list = v; }

        @Override
        public void run() {
            for (int i = 0; i < CAPACITY; i++) {
                //synchronized (list) {
                    int lastIndex = list.size() - 1;
                    list.get(lastIndex);
                //}
            }
        }
    }

    private static class ThreadDelete extends Thread {
        private Vector list;
        public ThreadDelete(Vector v) { this.list = v; }

        @Override
        public void run() {
            for (int i = 0; i < CAPACITY; i++) {
                //synchronized (list) {
                    int lastIndex = list.size() - 1;
                    list.remove(lastIndex);
                //}
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> v = new Vector<Integer>(CAPACITY + 1);
        for (int i = 0; i < CAPACITY + 1; i++) { v.add(i); }
        new ThreadDelete(v).start();
        new ThreadGet(v).start();
    }
}
