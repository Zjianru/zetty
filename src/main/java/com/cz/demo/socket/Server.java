package com.cz.demo.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * socket connect Server
 *
 * @author Zjianru
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // server 与 client 的通信线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("server is start ...");
        while (true) {
            // client 连接进入
            final Socket accept = serverSocket.accept();
            System.out.println("client is connected ... start process client msg ...");
            threadPool.execute(() -> serverHandle(accept));
        }
    }

    private static void serverHandle(Socket socket) {
        try {
            Thread currentThread = Thread.currentThread();
            System.out.println("thread id is " + currentThread.getId() + " and thread name is " + currentThread.getName());
            // 打印 client 发来的消息
            byte[] b = new byte[1024];
            int read = socket.getInputStream().read(b);
            String clientMsg = new String(b, 0, read).trim();
            System.out.println("client msg is " + clientMsg);
            // 返回消息给 client
            String result = " server has received client msg ..." + clientMsg;
            socket.getOutputStream().write(result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
