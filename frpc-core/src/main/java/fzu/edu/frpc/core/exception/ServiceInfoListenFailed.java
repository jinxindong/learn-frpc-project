package fzu.edu.frpc.core.exception;

import fzu.edu.frpc.core.until.Constant;

/**
 * @author jinxindong
 * @create 2018-06-19 15:05
 */

public class ServiceInfoListenFailed extends RuntimeException {
    public ServiceInfoListenFailed() {
        super(Constant.PUBLISH_LISTEN_FAILED);
    }
}
