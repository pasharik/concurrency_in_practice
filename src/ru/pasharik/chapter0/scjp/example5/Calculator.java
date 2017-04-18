package ru.pasharik.chapter0.scjp.example5;

/**
 * Created by pasharik on 18/04/17.
 */
public class Calculator extends Thread {
    int total;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) total += i;
            notifyAll();
        }
    }
}
