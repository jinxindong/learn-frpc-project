package fzu.edu.frpc.core.io.nio;

import fzu.edu.frpc.core.entity.RequestInfo;
import fzu.edu.frpc.core.service.ProviderServiceHelper;
import fzu.edu.frpc.core.service.impl.ProviderServiceHelperImpl;
import fzu.edu.frpc.core.until.Constant;
import fzu.edu.frpc.core.until.ObjectAndByteUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author jinxindong
 * @create 2018-06-21 13:01
 */

public class ServerThread extends NioBase implements Runnable {
    @Override
    public void run() {
        try {
            initSelector();
            initServer(Constant.IP, Constant.PORT);
            listen();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 获取serverSocket通道，并通过port对其进行初始化
     *
     * @param ip
     * @param port
     * @throws IOException
     */
    private void initServer(String ip, int port) throws IOException {
        // 获取 ServerSocketChannel
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 初始化
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(ip, port));
        // 注册channel到selector上 并对accept事件表示感兴趣 一旦有连接 就可以select()返回 否则一直阻塞
        channel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws Exception {
        System.out.println("监听开始，可以进行服务注册啦");
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 事件分类 1、服务端接收客户端事件 accept 2、客户端连接服务端事件 connect
            // 3、读事件 read 4、写事件 write
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove(); // 删除已选的key 防止重复
                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        // 获取对事件感兴趣的 ServerSocketChannel
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        // 获取和客户端连接的socketChannel
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws Exception {
        byte[] bytes = getReadData(key);
        if (Objects.isNull(bytes)) {
            return;
        }
        SocketChannel channel = (SocketChannel) key.channel();
        RequestInfo requestInfo = (RequestInfo) ObjectAndByteUtil.toObject(bytes);
        ProviderServiceHelper providerServiceHelper = new ProviderServiceHelperImpl();
        byte[] toByteArray = ObjectAndByteUtil.toByteArray(providerServiceHelper.call(requestInfo));// 调用正在的业务逻辑
        // 将结果写回
        channel.write(ByteBuffer.wrap(toByteArray));
    }
}
