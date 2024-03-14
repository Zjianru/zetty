package com.cz.demo.socket;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * socket connect client
 *
 * @author Zjianru
 */
public class Client {
    public static void main(String[] args) throws IOException {
        while (true) {
            // 链接指定 IP 端口
            Socket socket = new Socket("127.0.0.1", 9999);
            // 准备输入信息
            System.out.println(" please input");
            Scanner scanner = new Scanner(System.in);
            String inputMsg = scanner.nextLine();
            // 输入信息写入流
            socket.getOutputStream().write(inputMsg.getBytes(StandardCharsets.UTF_8));
            // 流中读取输出
            byte[] b = new byte[1024];
            int read = socket.getInputStream().read(b);
            System.out.println("result is " + new String(b, 0, read).trim());
            socket.close();
        }
    }
}
