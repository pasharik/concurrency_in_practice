package ru.pasharik.chapter7.Listing7_1;

/**
 * Created by pasharik on 11/01/18.
 */
public class PrimeStarter {
    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator g = new PrimeGenerator();
        new Thread(g).start();
        try {
            Thread.sleep(100);
        } finally {
            g.cancel();
        }
        System.out.println(g.getPrimes());
    }
}
