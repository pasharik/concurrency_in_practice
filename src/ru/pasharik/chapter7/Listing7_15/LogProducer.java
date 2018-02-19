package ru.pasharik.chapter7.Listing7_15;

import java.io.PrintWriter;

/**
 * Created by pasharik on 17/02/18.
 */
public class LogProducer {
    public static void main(String[] args) throws InterruptedException {
        LogService log = new LogService(new PrintWriter(System.out, true));
        log.start();
        log.log("a");
        log.log("ab");
        log.log("abc");
        log.log("abcd");
        log.log("abcde");
        System.out.println("Trying to stop...");
        log.stop();
        //log.log("abcdef"); //Don't do it: will throw exception. Logger is already shut down
    }
}
