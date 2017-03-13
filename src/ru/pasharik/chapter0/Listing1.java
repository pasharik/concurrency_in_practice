package ru.pasharik.chapter0;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pasharik on 13/03/17.
 */
public class Listing1 {
    public static void main(String[] args) {
        new Listing1().sort(Arrays.asList(1, 2));
    }

    public <T extends Comparable<? super T>> void sort(List<T> list) {
        System.exit(0);
    }
}
