package ru.pasharik.chapter2.Listing2_8;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;

/**
 * Created by pasharik on 25/03/17.
 */
@ThreadSafe
public class CachedFactorizer /*implements Servlet*/ {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hints;
    @GuardedBy("this") private long cacheHints;

    public synchronized long getHints() { return hints; }
    public synchronized double getCacheHintRatio() {
        return (double) cacheHints / (double) hints;
    }

    public void service() {
        BigInteger i = extractFromRequest();
        BigInteger[] factors = null;
        synchronized (this) {
            ++hints;
            if (i.equals(lastNumber)) {
                ++cacheHints;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
    }

    private BigInteger extractFromRequest() { return new BigInteger("0"); }
    private BigInteger[] factor(BigInteger i) { return new BigInteger[]{i}; }
}
