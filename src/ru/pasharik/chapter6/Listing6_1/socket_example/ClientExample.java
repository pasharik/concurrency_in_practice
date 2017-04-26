package ru.pasharik.chapter6.Listing6_1.socket_example;

import java.io.*;
import java.net.Socket;

/**
 * Created by pasharik on 25/04/17.
 */
public class ClientExample {
    public static void main(String[] args) throws IOException {
        new ClientExample().run();
    }

    public void run() throws IOException {
        Socket socket = new Socket("localhost", 4444);
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println("Hello to SERVER from CLIENT");

        InputStreamReader ir = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(ir);
        String message = reader.readLine();
        System.out.println(message);
    }
}
