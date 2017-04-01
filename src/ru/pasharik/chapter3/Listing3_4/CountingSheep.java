package ru.pasharik.chapter3.Listing3_4;

/**
 * Created by pasharik on 01/04/17.
 */
public class CountingSheep extends Thread {
    /*volatile */boolean asleep = false;

    @Override
    public void run() {
        while (!asleep) {
        }

        System.out.println("Thread terminated.");
    }

    public static void main(String[] args) throws InterruptedException {
        CountingSheep t = new CountingSheep();
        t.start();
        Thread.sleep(1000);
        t.asleep = true;
        System.out.println("asleep set to false.");
    }
}
