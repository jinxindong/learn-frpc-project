package fzu.edu.frpc.center.centerService;

import fzu.edu.frpc.center.entity.ServiceInfo;

import java.util.Set;

/**
 * @author jinxindong
 * @create 2018-06-11 13:41
 */
public interface CenterService {
    public boolean register(ServiceInfo serviceInfo);

    public Set<String> getServiceIPsById(String id);

    public ServiceInfo getServiceInfoById(String id);
}
