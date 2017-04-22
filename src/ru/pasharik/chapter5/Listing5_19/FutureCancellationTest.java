package ru.pasharik.chapter5.Listing5_19;

import java.util.concurrent.*;

/**
 * Created by pasharik on 22/04/17.
 */
public class FutureCancellationTest {
    private static class MyCallable implements Callable<Long> {
        public Long call() throws Exception {
            Thread.sleep(1000);
            return 1l;
        }
    }
    static FutureTask<Long> f = new FutureTask<Long>(new MyCallable());

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        new Thread() {
            public void run() {
                f.run();
            }
        }.start();

        new Thread() {
            public void run() {
                System.out.println("Is cancelled: " + f.isCancelled());
                f.cancel(true); //TODO: comment to get rid of CancellationException
                System.out.println("Is cancelled: " + f.isCancelled());
            }
        }.start();

        try {
            System.out.println(f.get());
        } catch (CancellationException ignored) { System.out.println("Calculation cancelled"); }
        Thread.sleep(2000);

        /*
        If calculation was cancelled on first attempt of get(),
        all next calls will throw CancellationException as well
         */
        System.out.println("2nd attempt: " + f.get());
    }
}
