package ru.pasharik.chapter7.Listing7_11;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by pasharik on 16/02/18.
 */
public class ServerThread extends Thread {
    private ServerSocket ss;

    public ServerThread() {
        try {
            ss = new ServerSocket(4444);
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void run() {
        try {
            ss.accept();
        } catch (IOException e) { /*Allow thread to exit*/ }
        System.out.println("Server shut down");
    }

    @Override
    public void interrupt() {
        try {
            ss.close();
        } catch (IOException ignored) {}
        finally {
            super.interrupt();
        }
    }
}
