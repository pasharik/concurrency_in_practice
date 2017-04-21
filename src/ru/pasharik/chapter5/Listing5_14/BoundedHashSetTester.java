package ru.pasharik.chapter5.Listing5_14;

/**
 * Created by pasharik on 21/04/17.
 */
public class BoundedHashSetTester {
    private static final BoundedHashSet<String> set = new BoundedHashSet<String>(10);

    private static class WriterT extends Thread {
        public void run() {
            for (int i = 0; i < 10;  i++) {
                try {
                    set.add("Added " + i);
                } catch (InterruptedException ignored) { }
                System.out.println("Added " + i);
            }

        }
    }

    private static class ReaderT extends Thread {
        public void run() {
            for (int i = 0; i < 10;  i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) { }
                set.remove("Added " + i);
                System.out.println("Removed " + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) set.add("" + i);
        new WriterT().start();
        new ReaderT().start();
    }
}
