package fzu.edu.frpc.core.exception;

import fzu.edu.frpc.core.until.Constant;

/**
 * @author jinxindong
 * @create 2018-06-11 18:57
 */

public class ServiceInfoNotComplete extends RuntimeException{
    public ServiceInfoNotComplete(){
        super(Constant.PUBLISH_NOT_COMPLETE);
    }
}
