package cn.itcast.netty;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void socketTest() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 10005);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        for (int i = 0; i < 100; i++) {
            pw.println("HelloWorld");
            pw.flush();
            TimeUnit.SECONDS.sleep(5);
        }
        pw.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        socketTest();
    }
}