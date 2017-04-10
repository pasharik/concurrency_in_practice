package ru.pasharik.chapter3.Listing3_14;

/**
 * Created by pasharik on 04/04/17.
 */
public class ConstructorDelayPublication {
    private static class SomeClass {
        public int i;
        public SomeClass() throws InterruptedException {
            InnerThread it = new InnerThread(SomeClass.this);
            it.start();
            Thread.sleep(1000);
            i = 200;
        }
    }

    private static class InnerThread extends Thread {
        private SomeClass sc;

        public InnerThread(SomeClass sc) {
            this.sc = sc;
        }

        @Override
        public void run() {
            System.out.println(sc.i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.out.println(sc.i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SomeClass someClass = new SomeClass();
    }
}
