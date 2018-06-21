package fzu.edu.frpc.core.io;

import fzu.edu.frpc.core.until.Constant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jinxindong
 * @create 2018-06-19 15:24
 */

public class ServerThread implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Constant.PORT);
            System.out.println("已经开始监了");
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerProcessThread(socket)).start();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
