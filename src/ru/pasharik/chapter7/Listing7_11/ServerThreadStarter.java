package ru.pasharik.chapter7.Listing7_11;

/**
 * Created by pasharik on 16/02/18.
 */
public class ServerThreadStarter {
    public static void main(String[] args) throws InterruptedException {
        ServerThread t = new ServerThread();
        t.start();
        Thread.sleep(1000);
        System.out.println("Trying to stop thread...");
        t.interrupt();
    }
}
