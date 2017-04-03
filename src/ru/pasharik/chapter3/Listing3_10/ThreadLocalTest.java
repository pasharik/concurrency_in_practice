package ru.pasharik.chapter3.Listing3_10;

/**
 * Created by pasharik on 03/04/17.
 */
public class ThreadLocalTest {
    private static class TestThread extends Thread {
        private int num;
        public TestThread(int num) { this.num = num; }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++)
                System.out.println("Thread: " + num + "; value: " + ThreadLocalNumberHolder.getNumber());
        }
    }

    public static void main(String[] args) {
        new TestThread(1).start();
        new TestThread(2).start();
    }
}
