package fzu.edu.frpc.core.service;

import fzu.edu.frpc.core.entity.RequestInfo;

import java.util.Set;

/**
 * @author jinxindong
 * @create 2018-06-19 16:57
 */

public interface ConsumerServiceHelper {

    public Set<String> getServiceIPsByID(String serviceId);

    public String getIp(String serviceId, String methodName, Object[] params, Set<String> ips);

    public RequestInfo getRequestInfo(String interfaceName, String version, String methodName, Object[] params);

    public Object sendData(String ip, RequestInfo requestInfo) throws Exception;
}
