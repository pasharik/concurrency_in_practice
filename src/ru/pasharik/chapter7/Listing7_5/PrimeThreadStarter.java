package ru.pasharik.chapter7.Listing7_5;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by pasharik on 14/01/18.
 */
public class PrimeThreadStarter {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> q = new LinkedBlockingQueue<>(10);
        PrimeProducerThread p = new PrimeProducerThread(q);
        p.start();
        try {
            Thread.sleep(100);
        } finally {
            p.cancel();
        }
        System.out.println(q);
    }
}
