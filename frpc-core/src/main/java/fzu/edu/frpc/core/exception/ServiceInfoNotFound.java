package fzu.edu.frpc.core.exception;

import fzu.edu.frpc.core.until.Constant;

/**
 * @author jinxindong
 * @create 2018-06-19 17:23
 */

public class ServiceInfoNotFound extends RuntimeException {
    public ServiceInfoNotFound() {
        super(Constant.SERVER_NOT_FOUND);
    }
}
