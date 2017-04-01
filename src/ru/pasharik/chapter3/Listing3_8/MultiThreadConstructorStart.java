package ru.pasharik.chapter3.Listing3_8;

/**
 * Created by pasharik on 01/04/17.
 */
public class MultiThreadConstructorStart {
    private Thread[] a;

    private MultiThreadConstructorStart() {
        a = new Thread[4];
        for (int i = 0; i < 4; i++) {
            a[i] = new Thread() {
                @Override
                public void run() {
                    System.out.println("Hello, world!");
                }
            };
        }
    }

    private void startAll() {
        for (int i = 0; i < 4; i++) { a[i].start(); }
    }

    /*
    Factory method prevents escape of "this" instance of MultiThreadConstructorStart class
    from constructor.
    If "this" escaped from constructor, it's not fully constructed yet
     */
    public static MultiThreadConstructorStart getInstance() {
        MultiThreadConstructorStart mtt = new MultiThreadConstructorStart();
        mtt.startAll();
        return mtt;
    }

    public static void main(String[] args) {
        MultiThreadConstructorStart.getInstance();
    }
}
