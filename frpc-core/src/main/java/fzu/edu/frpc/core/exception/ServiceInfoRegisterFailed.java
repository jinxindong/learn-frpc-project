package fzu.edu.frpc.core.exception;

import fzu.edu.frpc.core.until.Constant;

/**
 * @author jinxindong
 * @create 2018-06-11 18:57
 */

public class ServiceInfoRegisterFailed extends RuntimeException {
    public ServiceInfoRegisterFailed() {
        super(Constant.PUBLISH_REGISTER_FAILED);
    }
}
