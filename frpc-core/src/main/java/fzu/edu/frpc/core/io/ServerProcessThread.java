package fzu.edu.frpc.core.io;

import fzu.edu.frpc.core.entity.RequestInfo;
import fzu.edu.frpc.core.service.ProviderServiceHelper;
import fzu.edu.frpc.core.service.impl.ProviderServiceHelperImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author jinxindong
 * @create 2018-06-11 17:59
 */

public class ServerProcessThread implements Runnable {
    private Socket socket;

    public ServerProcessThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            // 反序列化为RequestInfo对象
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RequestInfo requestInfo = (RequestInfo) objectInputStream.readObject();

            // 利用helper 反射获取实例进行计算 获取最终结果
            ProviderServiceHelper providerServiceHelper = new ProviderServiceHelperImpl();
            Object result = providerServiceHelper.call(requestInfo);

            // 将结果序列化 写回调用端
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            socket.shutdownOutput();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
