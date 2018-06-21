package fzu.edu.frpc.core.service.impl;

import fzu.edu.frpc.core.entity.RequestInfo;
import fzu.edu.frpc.core.service.ConsumerServiceHelper;
import fzu.edu.frpc.core.until.Constant;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Set;

/**
 * @author jinxindong
 * @create 2018-06-19 17:02
 */

public class ConsumerServiceHelperImpl implements ConsumerServiceHelper {
    @Override
    public Set<String> getServiceIPsByID(String serviceId) {
        return null;
    }

    @Override
    public String getIp(String serviceId, String methodName, Object[] params, Set<String> ips) {
        //模拟路由过程
        String[] temp = new String[ips.size()];
        ips.toArray(temp);
        return temp[0];
    }

    @Override
    public RequestInfo getRequestInfo(String interfaceName, String version, String methodName, Object[] params) {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setVersion(version);
        requestInfo.setInterfaceName(interfaceName);
        requestInfo.setMethodName(methodName);
        requestInfo.setParams(params);
        return requestInfo;
    }

    @Override
    public Object sendData(String ip, RequestInfo requestInfo) throws Exception {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        Socket socket = null;
        try {
            socket = new Socket(ip, Constant.PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(requestInfo);// 对象写入到outputStream中
            outputStream.flush();
            outputStream.close();
            socket.shutdownOutput();

            inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            inputStream.close();
            socket.close();
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (Objects.nonNull(outputStream)) {
                    outputStream.close();
                }
                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
                if (Objects.nonNull(socket)) {
                    socket.close();
                }
            } catch (IOException ex) {
                throw ex;
            }
        }
    }
}
