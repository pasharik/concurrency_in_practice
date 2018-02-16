package ru.pasharik.chapter7.Listing7_12;

import net.jcip.annotations.GuardedBy;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * Created by pasharik on 16/02/18.
 */
public abstract class ServerSocketUsingTask implements CancellableTask<String> {
    @GuardedBy("this")
    private ServerSocket socket;

    protected synchronized void setSocket(ServerSocket s) { this.socket = s; }

    @Override
    public synchronized void cancel() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ignored) { }
    }

    @Override
    public RunnableFuture<String> newTask() {
        return new FutureTask<String>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    ServerSocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
