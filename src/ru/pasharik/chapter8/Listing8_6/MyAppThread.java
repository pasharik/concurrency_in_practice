package ru.pasharik.chapter8.Listing8_6;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pasharik on 09/03/18.
 */
public class MyAppThread extends Thread {
    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static AtomicInteger created = new AtomicInteger();
    private static AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable r) { this(r, DEFAULT_NAME); }

    public MyAppThread(Runnable r, String name) {
        super(r, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler((t, e) -> { log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e); });
    }

    public void run() {
        boolean debug = debugLifecycle;
        if (debug) log.log(Level.INFO, "Created " + getName());
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) log.log(Level.INFO, "Exiting " + getName());
        }
    }

    public static boolean isDebugLifecycle() { return debugLifecycle; }
    public static void setDebugLifecycle(boolean debugLifecycle) { MyAppThread.debugLifecycle = debugLifecycle; }
    public static int getThreadsAlive() { return alive.get(); }
    public static int getThreadsCreated() { return created.get(); }
}
