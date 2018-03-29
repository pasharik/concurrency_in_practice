package ru.pasharik.chapter8.Listing8_12;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasharik on 29/03/18.
 */
public class Node {
    public int value;

    public Node(int val) {
        this.value = val;
    }

    public List<Node> children = new ArrayList<>();
}
