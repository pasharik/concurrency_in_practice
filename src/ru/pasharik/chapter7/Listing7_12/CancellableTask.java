package ru.pasharik.chapter7.Listing7_12;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * Created by pasharik on 16/02/18.
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}
