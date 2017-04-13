package ru.pasharik.chapter5.Listing5_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pasharik on 13/04/17.
 */
public class ListIterationException {
    public static void main(String[] args) {
        List<Integer> intList = Collections.synchronizedList(new ArrayList<Integer>());
        for (int i = 0; i < 10; i++) intList.add(i);
        for (int k : intList) {
            if (k > 5) intList.add(k + 10);
            System.out.println(k);
        }
    }
}
