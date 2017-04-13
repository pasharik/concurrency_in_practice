package ru.pasharik.chapter5.Listing5_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by pasharik on 13/04/17.
 */
public class IterationParallel {
    private static final int CAPACITY = 100_000;

    private static class ThreadAdd extends Thread {
        private List<Integer> list;
        public ThreadAdd(List<Integer> l) { this.list = l; }

        @Override
        public void run() {
            for (int i = 0; i < CAPACITY; i++) list.add(i);
        }
    }

    private static class ThreadIterate extends Thread {
        private List<Integer> list;
        public ThreadIterate(List<Integer> l) { this.list = l; }

        @Override
        public void run() {
            runOrigin();
            //runClone();
        }

        private void runOrigin() {
            System.out.println(list.size());
            //synchronized (list) { //Uncomment to fix ConcurrentModificationException
            for (int k : list) { doNothing(k); }
            //}
        }

        /* Makes copy of the original collection and uses it for iteration.
        Can improve performance if action on each element is time consuming */
        private void runClone() {
            List<Integer> listClone;
            synchronized (list) {
                listClone = new ArrayList<>(list);
            }
            System.out.println(listClone.size());
            for (int k : listClone) doNothing(k);
        }
    }

    private static void doNothing(int j) {}

    public static void main(String[] args) {
        List<Integer> intList = Collections.synchronizedList(new ArrayList<Integer>());
        for (int i = 0; i < 10; i++) intList.add(i);
        new ThreadAdd(intList).start();
        new ThreadIterate(intList).start();
    }
}
