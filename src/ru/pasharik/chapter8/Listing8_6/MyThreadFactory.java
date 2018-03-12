package ru.pasharik.chapter8.Listing8_6;

import java.util.concurrent.ThreadFactory;

/**
 * Created by pasharik on 09/03/18.
 */
public class MyThreadFactory implements ThreadFactory {
    private String poolName;
    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }
    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
