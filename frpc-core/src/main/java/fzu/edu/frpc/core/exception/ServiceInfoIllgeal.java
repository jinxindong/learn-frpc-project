package fzu.edu.frpc.core.exception;

import fzu.edu.frpc.core.until.Constant;

/**
 * @author jinxindong
 * @create 2018-06-19 17:16
 */

public class ServiceInfoIllgeal extends RuntimeException {
    public ServiceInfoIllgeal() {
        super(Constant.NAME_VERSION_NOT_LEGAL);
    }
}
