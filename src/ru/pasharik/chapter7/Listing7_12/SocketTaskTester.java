package ru.pasharik.chapter7.Listing7_12;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by pasharik on 16/02/18.
 */
public class SocketTaskTester {
    private static class ServerSocketCall extends ServerSocketUsingTask {
        private ServerSocket socket;
        public ServerSocketCall(ServerSocket s) {
            this.socket = s;
            setSocket(s);
        }

        @Override
        public String call() throws Exception {
            System.out.println("Waiting for socket...");
            try {
                socket.accept();
            } catch (SocketException e) {
                System.out.println("Socket closed");
            }
            System.out.println("Returning from task");
            return "";
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService service = new CancellingThreadPoolExecutor(1);
        Future<String> f = service.submit(new ServerSocketCall(new ServerSocket(4444)));
        Thread.sleep(1000);
        System.out.println("Starting to interrupt thread...");
        f.cancel(true);
        Thread.sleep(4000);
        System.out.println("Shutting down thread pool");
        service.shutdown();
    }
}
