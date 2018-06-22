package fzu.edu.frpc.core.until;

import java.io.*;

/**
 * @author jinxindong
 * @create 2018-06-21 14:30
 */

public class ObjectAndByteUtil {

    public static byte[] toByteArray(Object object) {
        byte[] bytes = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream inputStream = new ObjectOutputStream(outputStream);
            inputStream.writeObject(object);
            inputStream.flush();
            bytes = outputStream.toByteArray();
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    public static Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            obj = objectInputStream.readObject();
            inputStream.close();
            objectInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }
}
