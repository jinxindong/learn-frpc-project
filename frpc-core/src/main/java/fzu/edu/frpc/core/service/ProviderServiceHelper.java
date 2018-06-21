package fzu.edu.frpc.core.service;

import fzu.edu.frpc.core.entity.RequestInfo;

/**
 * @author jinxindong
 * @create 2018-06-11 17:23
 */

public interface ProviderServiceHelper {

    public boolean startListen();

    public boolean registry(String interfaceName, String version, String implClassName, String ip);

    public Object call(RequestInfo requestInfo)throws Exception;
}
