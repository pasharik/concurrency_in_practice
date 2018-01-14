package ru.pasharik.chapter7.Listing7_5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by pasharik on 14/01/18.
 */
public class QueueInterruptionTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> q = new LinkedBlockingQueue<>();
        Thread.currentThread().interrupt();
        System.out.println("Interrupeted before q.take(): " + Thread.currentThread().isInterrupted());
        try {
            q.take();
        } catch (InterruptedException e) {
            System.out.println("Exception!!!111");
            System.out.println("Interrupeted after q.take(): " + Thread.currentThread().isInterrupted());
            //Need to restore interrupted here
        }
        System.out.println("Interrupeted after expeption block: " + Thread.currentThread().isInterrupted());
    }
}
