package ru.pasharik.chapter15.Listing15_2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.pasharik.chapter15.Listing15_1.SimulatedCAS;

/**
 * Created by pasharik on 09/08/18.
 */
@ThreadSafe
public class CasCounter {
    @GuardedBy("this")
    private SimulatedCAS value;

    public CasCounter() { value = new SimulatedCAS(0); }

    public int getValue() { return value.getValue(); }

    public int increment() {
        int v;
        do {
            v = value.getValue();
        } while (v != value.compareAndSwap(v, v+1));
        //} while (!value.compareAndSet(v, v+1));
        return v + 1;
    }

    public static void main(String[] args) {
        CasCounter c = new CasCounter();
        for (int i = 0; i < 10; i++) {
            c.increment();
        }
        System.out.println(c.getValue());
    }
}
