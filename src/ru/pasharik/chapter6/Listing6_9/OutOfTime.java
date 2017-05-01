package ru.pasharik.chapter6.Listing6_9;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by pasharik on 01/05/17.
 */
public class OutOfTime {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(5);
        timer.cancel();

        //TODO: uncomment to fix
        /*ScheduledExecutorService ex = Executors.newScheduledThreadPool(1);
        ex.schedule(new ThrowTask(), 1, TimeUnit.MILLISECONDS);
        SECONDS.sleep(1);
        ex.schedule(new ThrowTask(), 1, TimeUnit.MILLISECONDS);
        SECONDS.sleep(5);
        ex.shutdown();*/
    }

    static class ThrowTask extends TimerTask {
        public void run() {
            System.out.println("Task");
            throw new RuntimeException();
        }
    }
}
