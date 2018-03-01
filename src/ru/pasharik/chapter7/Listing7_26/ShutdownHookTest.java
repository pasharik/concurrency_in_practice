package ru.pasharik.chapter7.Listing7_26;

/**
 * Created by pasharik on 01/03/18.
 */
public class ShutdownHookTest {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutdown hook!");
            }
        });

        Thread t = new Thread() {
            public void run() {
                try {
                    System.out.println("Going to sleep...");
                    Thread.sleep(5000);
                    System.out.println("Wake up!");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!"); //Never get here - there is no interruption on JVM shutdown
                } finally {
                    System.out.println("Finishing sleeping thread"); //Never get here - if thread didn't finish on time
                    //during JVM termination, it just halts. No final blocks executed
                }
            }
        };
        t.start();

        //Runtime.getRuntime().halt(0); //Abrupt exit. In this case even shutdown hook not called
        System.exit(0); //Exiting orderly
    }
}
