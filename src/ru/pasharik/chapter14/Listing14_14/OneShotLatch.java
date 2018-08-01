package ru.pasharik.chapter14.Listing14_14;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by pasharik on 01/08/18.
 */
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    public void signal() { sync.releaseShared(0); }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }
}
