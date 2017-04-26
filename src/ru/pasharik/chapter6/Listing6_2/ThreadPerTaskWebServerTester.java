package ru.pasharik.chapter6.Listing6_2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by pasharik on 26/04/17.
 */
public class ThreadPerTaskWebServerTester {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            Socket socket = new Socket("localhost", 8080);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Hello " + i);
        }
    }
}
