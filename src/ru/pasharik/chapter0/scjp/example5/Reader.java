package ru.pasharik.chapter0.scjp.example5;

/**
 * Created by pasharik on 18/04/17.
 */
public class Reader extends Thread {
    private final Calculator c;

    public Reader(Calculator c) {
        this.c = c;
    }

    public void run() {
        synchronized (c) {
            System.out.println("Waiting for calculation... " + Thread.currentThread().getName());
            try {
                c.wait();
            } catch (InterruptedException e) {}
            System.out.println("Total is: " + c.total + " " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        new Reader(cal).start();
        new Reader(cal).start();
        new Reader(cal).start();
        cal.start();
    }
}
