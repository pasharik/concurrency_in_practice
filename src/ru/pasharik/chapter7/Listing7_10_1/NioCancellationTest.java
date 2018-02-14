package ru.pasharik.chapter7.Listing7_10_1;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.*;

/**
 * Created by pasharik on 14/02/18.
 */
public class NioCancellationTest {
    private static class NioFileWriter implements Runnable {
        @Override
        public void run() {
            Path path = Paths.get("/home/pasharik/question.txt");
            String question = "To be or not to be?";
            try {
                Files.write(path, question.getBytes());
                for (int i = 0; i < 1_000_000; i++) {
                    Files.write(path, question.getBytes(), StandardOpenOption.APPEND);
                }
            } catch (ClosedByInterruptException e) {
                //Thread was interrupted.
                System.out.println("Exiting...");
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new NioFileWriter());
        t.start();
        Thread.sleep(1000);
        System.out.println("Trying to interrupt...");
        t.interrupt(); //Interrupt thread before it has completed all 1M writes
    }
}
