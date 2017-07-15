package ru.pasharik.chapter0.crawling.developerslife;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pasharik on 10/05/17.
 */
public class Crawler {
    private static final int NUM_THREADS = 4;
    private final ConcurrentMap<Integer, Pirojok> map = new ConcurrentHashMap<>(); //Just to ensure uniqueness
    private final BlockingQueue<List<Pirojok>> q = new LinkedBlockingQueue<List<Pirojok>>(); //To store pages when ready
    private final ExecutorService service = Executors.newFixedThreadPool(NUM_THREADS);
    private final AtomicInteger pageNum = new AtomicInteger(1);
    private static final int PAGE_LIMIT = 4;
    private static final int LOWER_RATING = 400;
    private static final int UPPER_RATING = 900;

    private class CrawlerRun implements Runnable {
        @Override
        public void run() {
            int pNum = pageNum.getAndIncrement();
            while (pNum <= PAGE_LIMIT) {
                List<Pirojok> list = Parser.parse(pNum);
                List<Pirojok> res = new ArrayList<>();
                for (Pirojok p : list) {
                    if (map.putIfAbsent(p.getId(), p) == null) res.add(p);
                }
                pNum = pageNum.getAndIncrement();
                q.offer(res);
            }
        }
    };

    public void start() throws ExecutionException, InterruptedException {
        for (int i = 0; i < NUM_THREADS; i++) {
            service.submit(new CrawlerRun());
        }
        service.shutdown();

        for (int i = 0; i < PAGE_LIMIT; i++) {
            for (Pirojok pir : q.take()) {
                if (pir.getRating() > LOWER_RATING && pir.getRating() < UPPER_RATING) {
                    System.out.println(pir);
                }
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        new Crawler().start();
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}
