package fzu.edu.frpc.core.io.nio;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @author jinxindong
 * @create 2018-06-21 13:01
 */

public class NioBase {
    public Selector selector;// 线程通道管理器

    public void initSelector() throws Exception {
        this.selector = Selector.open();
    }

    // 服务端接收数据
    public byte[] getReadData(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);// 随便写的一个数字
        int len = socketChannel.read(byteBuffer);// 从通道读到buffer中
        if (len == -1) {
            socketChannel.close();
            return null;
        }
        int lenth = 0;// 计算总长度
        List<byte[]> list = Lists.newArrayList();// 全部数据读取到list中
        while (len > 0) {
            lenth = lenth + len;
            byteBuffer.flip();// 重置
            byte[] arr = new byte[len];
            byteBuffer.get(arr, 0, len);//缓冲区内数据 写入到数组中
            list.add(arr);
            byteBuffer.clear();
            len = socketChannel.read(byteBuffer);
        }
        byte[] result = new byte[lenth];// 统一写入到一个里面
        int l = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                result[l + j] = list.get(i)[j];
            }
            l = l + list.get(i).length;
        }
        return result;
    }
}
