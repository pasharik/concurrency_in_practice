package ru.pasharik.chapter6.Listing6_6;

import java.util.concurrent.Executor;

/**
 * Created by pasharik on 29/04/17.
 */
public class WithinThreadExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
