package fzu.edu.frpc.core.openserver;

/**
 * @author jinxindong
 * @create 2018-06-19 16:52
 */

public interface FRPCConsumer {
    public Object serviceConsumer(String methodName, Object[] params) throws Exception;
}
