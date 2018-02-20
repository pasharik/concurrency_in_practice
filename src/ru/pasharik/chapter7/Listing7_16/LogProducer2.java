package ru.pasharik.chapter7.Listing7_16;

import java.io.PrintWriter;

/**
 * Created by pasharik on 20/02/18.
 */
public class LogProducer2 {
    public static void main(String[] args) throws InterruptedException {
        LogServiceWithExecutor log = new LogServiceWithExecutor(new PrintWriter(System.out, true));
        log.log("a");
        log.log("ab");
        log.log("abc");
        log.log("abcd");
        log.stop();
        log.log("ignored");
    }
}
