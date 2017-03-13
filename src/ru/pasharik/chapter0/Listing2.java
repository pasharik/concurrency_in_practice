package ru.pasharik.chapter0;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by pasharik on 13/03/17.
 */
public class Listing2 {
    public static void main(String[] args) {
        new Listing2().sort(Arrays.asList(2, 1, 5));
    }

    public <T extends Comparable<? super T>> void sort(List<T> list) {
        for (int i = 0; i < 1000000; i++)
            doNothing();
        Collections.sort(list);
    }

    private void doNothing() {
    }
}
