package fzu.edu.frpc.core.exception;

import fzu.edu.frpc.core.until.Constant;

/**
 * @author jinxindong
 * @create 2018-06-19 17:55
 */

public class ServiceInfoCallException extends RuntimeException {
    public ServiceInfoCallException() {
        super(Constant.SERVER_CALL_EXCEPTION);
    }
}
