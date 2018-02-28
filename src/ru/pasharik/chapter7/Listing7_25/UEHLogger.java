package ru.pasharik.chapter7.Listing7_25;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pasharik on 28/02/18.
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger log = Logger.getAnonymousLogger();
        log.log(Level.SEVERE, "Thread terminated with exception: " + t.getName(), e);
    }
}
