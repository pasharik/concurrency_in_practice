package ru.pasharik.chapter6.Listing6_15;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by pasharik on 06/05/17.
 */
public class Renderer {
    private static final ExecutorService executor = Executors.newFixedThreadPool(100);
    private static final Random r = new Random();

    private void render() {
        CompletionService<String> service = new ExecutorCompletionService(executor);
        for (int i = 0; i < 10; i++) {
            service.submit(new Callable<String>() {
                public String call() {
                    int waitInterval = r.nextInt(30);
                    try {
                        Thread.sleep(100 * waitInterval);
                    } catch (InterruptedException ignored) {
                        Thread.currentThread().interrupt();
                    }
                    return "Thread #: " + Thread.currentThread().getId() + " | Waided for: " + waitInterval;
                }
            });
        }

        System.out.println("Rendering started...");

        try {
            for (int i = 0; i < 10; i++) {
                Future<String> future = service.take();
                String res = future.get();
                System.out.println(res);
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException ee) {
            throw new RuntimeException(ee.getCause());
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        new Renderer().render();
    }
}
