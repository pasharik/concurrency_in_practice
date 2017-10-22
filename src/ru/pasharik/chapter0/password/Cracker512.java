package ru.pasharik.chapter0.password;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pasharik on 19/07/17.
 */
public class Cracker512 {
    public static final String pwd = "tesla";
    public static String TARGET = Hex.getSHA512SecurePassword(pwd);
    public static char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static int pwdMaxLen = 6;
    public static final int NUM_THREADS = 2;
    public static final AtomicBoolean stop = new AtomicBoolean(false);
    private static Long startTime = System.currentTimeMillis();
    private static AtomicLong ii = new AtomicLong(0);

    private final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    private final ExecutorCompletionService<Boolean> service = new ExecutorCompletionService<Boolean>(executor);

    private class CrackRun implements Callable<Boolean> {
        private long pow;
        private int pwdlen;
        public CrackRun(long pow, int pwdlen) {
            this.pow = pow;
            this.pwdlen = pwdlen;
        }
        @Override
        public Boolean call() {
            long t = 0;
            while ((t = ii.getAndIncrement()) < pow && !stop.get()) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < pwdlen; j++) {
                    sb.append(chars[(int) t % chars.length]);
                    t /= chars.length;
                }

                if (tryPass(sb.toString())) {
                    System.out.println(sb.toString());
                    stop.set(true);
                    System.out.println("Time: " + (System.currentTimeMillis() - startTime));
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, ExecutionException, InterruptedException {
        startTime = System.currentTimeMillis();
        new Cracker512().doCrack();
    }


    public void doCrack() throws NoSuchAlgorithmException, ExecutionException, InterruptedException {
        for (int pwdlen = 1; pwdlen <= pwdMaxLen; pwdlen++) {
            System.out.println("Pwd len: " + pwdlen);
            ii.set(0);
            long pow = (long) Math.pow(chars.length, pwdlen);

            for (int k = 0; k < NUM_THREADS; k++) {
                service.submit(new CrackRun(pow, pwdlen));
            }

            for (int k = 0; k < NUM_THREADS; k++) {
                if (service.take().get()) {
                    executor.shutdown();
                    return;
                }
            }
        }
    }

    public static boolean tryPass(String password) /*throws NoSuchAlgorithmException*/ {
        return TARGET.equals(Hex.getSHA512SecurePassword(password));
    }

}
