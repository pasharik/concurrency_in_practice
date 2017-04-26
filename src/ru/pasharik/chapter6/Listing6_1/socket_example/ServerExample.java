package ru.pasharik.chapter6.Listing6_1.socket_example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pasharik on 25/04/17.
 */
public class ServerExample {
    public static void main(String[] args) throws IOException {
        new ServerExample().run();
    }

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);
        Socket sock = serverSocket.accept();
        InputStreamReader ir = new InputStreamReader(sock.getInputStream());
        BufferedReader reader = new BufferedReader(ir);

        String message = reader.readLine();
        System.out.println(message);

        if (message != null) {
            PrintStream ps = new PrintStream(sock.getOutputStream());
            ps.println("Message received!");
        }
    }
}
