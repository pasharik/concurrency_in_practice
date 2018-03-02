package ru.pasharik.chapter8.Listing8_1;

import java.util.concurrent.*;

/**
 * Created by pasharik on 02/03/18.
 */
public class ThreadDeadlock {
    private ExecutorService exec = Executors.newSingleThreadExecutor(); //Use newFixedThreadPool(2) to avoid deadlock

    private class PageRender implements Callable<String> {
        public String call() throws Exception {
            Future<String> header = exec.submit(new Callable<String>() {
                public String call() throws Exception { return "Header\n"; } });
            System.out.println("Submitted header");
            Future<String> footer = exec.submit(new Callable<String>() {
                public String call() throws Exception { return "Footer"; } });
            System.out.println("Submitted footer");
            //Deadlock here - can't execute header task because executor supports only one thread at a time
            return header.get() + "\n" + "Page body here\n" + footer.get();
        }
    }

    private String renderPage() throws ExecutionException, InterruptedException {
        return exec.submit(new PageRender()).get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(new ThreadDeadlock().renderPage());
    }
}
