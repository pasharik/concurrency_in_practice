package ru.pasharik.chapter6.Listing6_5;

import java.util.concurrent.Executor;

/**
 * Created by pasharik on 29/04/17.
 */
public class ThreadPerTaskExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
