package fzu.edu.frpc.core.exception;

import fzu.edu.frpc.core.until.Constant;

/**
 * @author jinxindong
 * @create 2018-06-19 17:55
 */

public class ServiceInfoRemoteCallException extends RuntimeException {
    public ServiceInfoRemoteCallException() {
        super(Constant.SERVER_REMOTE_CALL_EXCEPTION);
    }
}
