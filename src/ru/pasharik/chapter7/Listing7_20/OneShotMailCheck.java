package ru.pasharik.chapter7.Listing7_20;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by pasharik on 24/02/18.
 */
public class OneShotMailCheck {
    public static void main(String[] args) throws InterruptedException {
        Set<String> hosts = new HashSet<>(Arrays.asList("localhost", "host1", "host2", "host3"));
        boolean hasNew = new OneShotMailCheck().hasNewMails(hosts, 1000L, TimeUnit.MILLISECONDS);
        System.out.println("Has new mail: " + hasNew);
    }

    private boolean hasNewMails(Set<String> hosts, Long timeout, TimeUnit timeUnit) throws InterruptedException {
        final AtomicBoolean hasMail = new AtomicBoolean(false);
        ExecutorService exec = Executors.newCachedThreadPool();
        try {
            for (final String host : hosts) {
                exec.submit(new Runnable() {
                    @Override
                    public void run() {
                        if (checkMail(host)) hasMail.set(true);
                    }
                });
            }
        } finally {
            exec.shutdown();
            exec.awaitTermination(timeout, timeUnit);
            exec.shutdownNow();
        }
        return hasMail.get();
    }

    private boolean checkMail(String host) {
        try {
            long time = 1000L * new Random().nextInt(5);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Interrupted... " + host);
            Thread.currentThread().interrupt();
        }
        return new Random().nextBoolean();
    }
}
