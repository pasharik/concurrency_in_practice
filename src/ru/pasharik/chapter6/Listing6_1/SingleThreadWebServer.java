package ru.pasharik.chapter6.Listing6_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pasharik on 24/04/17.
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        Socket connection = socket.accept();
    }
}
