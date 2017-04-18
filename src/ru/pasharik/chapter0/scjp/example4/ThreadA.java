package ru.pasharik.chapter0.scjp.example4;

/**
 * Created by pasharik on 18/04/17.
 */
public class ThreadA {
    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();

        //Exactly the same can be done with b.join()
        synchronized (b) {
            try {
                System.out.println("Waiting for b to complete...");
                b.wait();
            } catch (InterruptedException e) {
            }
            System.out.println("Total is: " + b.total);
        }
    }
}
