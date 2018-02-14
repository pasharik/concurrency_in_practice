package ru.pasharik.chapter7.Listing7_10_1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by pasharik on 14/02/18.
 */
public class OldFashionedFileCancellationTest {
    private static class OldFashionFileWriter implements Runnable {
        @Override
        public void run() {
            try {
                PrintWriter writer = new PrintWriter("/home/pasharik/question-old.txt");
                String question = "To be or not to be?";
                for (int i = 0; i < 10_000_000; i++) {
                    writer.write(question);
                }
                writer.close();
                System.out.println("Finished writing");
            } catch (FileNotFoundException e) { e.printStackTrace(); }


        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new OldFashionFileWriter());
        t.start();
        Thread.sleep(100);
        System.out.println("Trying to interrupt...");
        t.interrupt(); //Interrupt thread before it has completed all 1M writes
    }
}
