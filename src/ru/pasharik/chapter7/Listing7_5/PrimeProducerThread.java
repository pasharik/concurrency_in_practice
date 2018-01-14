package ru.pasharik.chapter7.Listing7_5;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pasharik on 14/01/18.
 */
public class PrimeProducerThread extends Thread {
    private BlockingQueue<BigInteger> q;

    public PrimeProducerThread(BlockingQueue<BigInteger> q) {
        this.q = q;
    }

    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                q.put(p = p.nextProbablePrime());
            } catch (InterruptedException consumed) {
                //This is important: q.put() seems to set interrupted flag back to false and throw exception
                Thread.currentThread().interrupt();
            }
        }
    }

    public void cancel() {
        interrupt();
    }
}
