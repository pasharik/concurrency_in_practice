package ru.pasharik.chapter3.Listing3_10;

/**
 * Created by pasharik on 03/04/17.
 */
public class ThreadLocalNumberHolder {
    private static ThreadLocal<Integer> numberHolder = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            System.out.println("New thread!");
            Double d = (Math.random() * 1000);
            return d.intValue();
        }
    };

    public static Integer getNumber() {
        return numberHolder.get();
    }
}
