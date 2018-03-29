package ru.pasharik.chapter8.Listing8_12;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pasharik on 29/03/18.
 */
public class TreeTester {
    private static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Node root = new Node(1);
        fill(root, 0);

        int sum = new ParallelTree(root).doCompute();
        System.out.println("Sum: " + sum);
    }

    private static void fill(Node n, int level) {
        if (level > 3) return;

        for (int i = 0; i < 3; i++) {
            Node child = new Node(num.getAndIncrement());
            n.children.add(child);
            fill(child, ++level);
        }
    }
}
