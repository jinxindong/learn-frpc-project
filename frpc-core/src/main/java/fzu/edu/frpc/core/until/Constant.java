package fzu.edu.frpc.core.until;

/**
 * @author jinxindong
 * @create 2018-06-11 17:45
 */

public class Constant {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 8082;
    public static final String REGISTER_CENTER_IP = "127.0.0.1";
    public static final int REGISTER_CENTER_PORT = 8080;

    public static final String REGISTER_CENTER_REGISTER = "/center/register";
    public static final String REGISTER_CENTER_QUERY_IPS = "/center/getServiceIPsById";
    public static final String REGISTER_CENTER_QUERY_SERVER_INFO = "/center/getServiceInfoById";

    public static final String PUBLISH_NOT_COMPLETE = "发布所需参数不完整";
    public static final String PUBLISH_REGISTER_FAILED = "发布服务注册失败";
    public static final String PUBLISH_LISTEN_FAILED = "服务开启监听失败";
    public static final String NAME_VERSION_NOT_LEGAL = "接口名称或者版本不合法";
    public static final String SERVER_NOT_FOUND = "服务没找到";
    public static final String SERVER_CALL_EXCEPTION = "调用过程中的各种异常";
    public static final String SERVER_REMOTE_CALL_EXCEPTION = "远程服务未知异常s";
}
