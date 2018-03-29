package ru.pasharik.chapter8.Listing8_12;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pasharik on 29/03/18.
 */
public class ParallelTree {
    private Node root;
    private ExecutorService exec = Executors.newFixedThreadPool(8);
    private AtomicInteger sum = new AtomicInteger();
    private AtomicInteger numTasks = new AtomicInteger(0);
    private CountDownLatch isDone = new CountDownLatch(1);

    public ParallelTree(Node root) {
        this.root = root;
    }

    public int doCompute() throws InterruptedException {
        computeParallel(Arrays.asList(root), sum);
        isDone.await();
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.SECONDS);
        return sum.get();
    }

    private void computeParallel(List<Node> nodes, AtomicInteger result) {
        for (Node n : nodes) {
            submitTask(n);
            computeParallel(n.children, result);
        }
    }

    private void submitTask(Node n) {
        numTasks.incrementAndGet();
        exec.submit(() -> compute(n) );
    }

    private void compute(Node n) {
        try {
            Thread.sleep(1000);
            sum.addAndGet(n.value);
            System.out.println(n.value + " \t| " + sum.get());
        } catch (InterruptedException ignored) { }
        finally {
            if(numTasks.decrementAndGet() == 0) { isDone.countDown(); }
        }
    }
}
