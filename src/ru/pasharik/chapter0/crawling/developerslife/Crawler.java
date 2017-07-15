package ru.pasharik.chapter0.crawling.developerslife;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pasharik on 10/05/17.
 */
public class Crawler {
    private static final int NUM_THREADS = 4;
    private final ConcurrentMap<Integer, Pirojok> map = new ConcurrentHashMap<>();
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
                for (Pirojok p : list) {
                    map.put(p.getId(), p);
                }
                pNum = pageNum.getAndIncrement();
            }
        }
    };

    public void start() throws ExecutionException, InterruptedException {
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            futures.add(service.submit(new CrawlerRun()));
        }
        for (Future f : futures) f.get();
        service.shutdown();

        for (Map.Entry<Integer, Pirojok> entry : map.entrySet()) {
            Pirojok pir = entry.getValue();
            if (pir.getRating() > LOWER_RATING && pir.getRating() < UPPER_RATING) {
                System.out.println(pir);
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        new Crawler().start();
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}
