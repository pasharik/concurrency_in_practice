package ru.pasharik.chapter7.Listing7_1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasharik on 11/01/18.
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancel;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancel) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() { cancel = true; }

    public synchronized List<BigInteger> getPrimes() {
        return new ArrayList<>(primes);
    }
}
