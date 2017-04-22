package ru.pasharik.chapter5.Listing5_19;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.*;

/**
 * Created by pasharik on 22/04/17.
 */
@ThreadSafe
public class Memorizer<A, V> implements Computable<A, V> {
    private final Computable<A, V> c;
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    public Memorizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> cal = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(cal);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
