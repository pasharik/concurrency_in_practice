package ru.pasharik.chapter3.Listing3_13;

import net.jcip.annotations.ThreadSafe;
import ru.pasharik.chapter3.Listing3_12.OneValueCache;

import java.math.BigInteger;

/**
 * Created by pasharik on 04/04/17.
 */
@ThreadSafe
public class VolatileCachedFactorizer /*implements Servlet*/ {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    public void service(BigInteger num) {
        BigInteger i = num;
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        //encodeIntoResponse(resp, factors);
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{};
    }
}
