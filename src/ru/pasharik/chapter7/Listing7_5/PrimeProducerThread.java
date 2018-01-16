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
        try {
            while (!Thread.currentThread().isInterrupted())
                q.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) { }
    }

    public void cancel() {
        interrupt();
    }
}
