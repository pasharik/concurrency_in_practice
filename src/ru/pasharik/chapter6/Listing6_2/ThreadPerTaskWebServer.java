package ru.pasharik.chapter6.Listing6_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pasharik on 26/04/17.
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(4000);

                        InputStreamReader ir = new InputStreamReader(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(ir);

                        System.out.println("Thread: " + Thread.currentThread().getName() + " | " + reader.readLine());
                    }
                    catch (InterruptedException | IOException e) { throw new RuntimeException(e); }
                }
            };
            new Thread(task).start();
        }
    }
}
