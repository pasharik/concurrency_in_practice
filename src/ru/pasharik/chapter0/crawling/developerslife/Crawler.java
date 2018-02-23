package ru.pasharik.chapter0.crawling.developerslife;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by pasharik on 10/05/17.
 */
public class Crawler {
    private static final int NUM_THREADS = 4;
    private final ConcurrentMap<Integer, Pirojok> map = new ConcurrentHashMap<>(); //Just to ensure uniqueness
    private final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    private final ExecutorCompletionService<List<Pirojok>> service = new ExecutorCompletionService<List<Pirojok>>(executor);
    private static final int PAGE_LIMIT = 4;
    private static final int LOWER_RATING = 400;
    private static final int UPPER_RATING = 900;
    private static final int TIME_BUDGET = 8_000;

    private class CrawlerRun implements Callable<List<Pirojok>> {
        int pageNum;
        public CrawlerRun(int pageNum) { this.pageNum = pageNum; }

        @Override
        public List<Pirojok> call() throws Exception {
            List<Pirojok> res = new ArrayList<>();
            for (Pirojok p : Parser.parse(pageNum)) {
                if (map.putIfAbsent(p.getId(), p) == null) res.add(p);
            }
            return res;
        }
    };

    public void start() throws ExecutionException, InterruptedException {
        for (int pNum = 1; pNum <= PAGE_LIMIT; pNum++) {
            service.submit(new CrawlerRun(pNum));
        }

        long endTime = System.currentTimeMillis() + TIME_BUDGET;
        for (int i = 1; i <= PAGE_LIMIT; i++) {
            long timeLeft = endTime - System.currentTimeMillis();
            Future<List<Pirojok>> f = service.poll(timeLeft, TimeUnit.MILLISECONDS);
            if (f == null) break;
            for (Pirojok pir : f.get()) {
                if (pir.getRating() > LOWER_RATING && pir.getRating() < UPPER_RATING) {
                    System.out.println(pir);
                }
            }
            System.out.println("--------- Page " + i + " ---------"); //Can be different page id, shows number of pages parsed so far
        }
        executor.shutdownNow();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        new Crawler().start();
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}
