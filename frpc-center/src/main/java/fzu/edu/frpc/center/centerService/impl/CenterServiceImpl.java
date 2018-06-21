package fzu.edu.frpc.center.centerService.impl;

import fzu.edu.frpc.center.centerService.CenterService;
import fzu.edu.frpc.center.entity.ServiceInfo;
import fzu.edu.frpc.center.store.ServiceStore;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * @author jinxindong
 * @create 2018-06-11 13:42
 */
@Service
public class CenterServiceImpl implements CenterService {
    @Override
    public boolean register(ServiceInfo serviceInfo) {
        if (!parameterCheck(serviceInfo)) {
            return false;
        }
        String serviceId = serviceInfo.getInterfaceName() + "_" + serviceInfo.getVersion();
        //注册表中含有该服务 则往该服务的ip列表中增加新的ip set自动去重
        if (ServiceStore.getInstance().getServices().containsKey(serviceId)) {
            ServiceStore.getInstance().getServices().get(serviceId).getIps().add(serviceInfo.getIp());
        } else {
            serviceInfo.getIps().add(serviceInfo.getIp());// 往set中加入 ip
            ServiceStore.getInstance().getServices().put(serviceId, serviceInfo);
        }
        return true;
    }

    private boolean parameterCheck(ServiceInfo serviceInfo) {
        if (Objects.isNull(serviceInfo)) {
            return false;
        }
        if (StringUtils.isEmpty(serviceInfo.getVersion()) ||
                StringUtils.isEmpty(serviceInfo.getInterfaceName()) ||
                StringUtils.isEmpty(serviceInfo.getImplClassName()) ||
                StringUtils.isEmpty(serviceInfo.getIp()) ||
                CollectionUtils.isEmpty(serviceInfo.getIps())) {
            return false;
        }
        return true;
    }


    @Override
    public Set<String> getServiceIPsById(String id) {
        if (ServiceStore.getInstance().getServices().containsKey(id)) {
            return ServiceStore.getInstance().getServices().get(id).getIps();
        }
        return Collections.EMPTY_SET;
    }


    @Override
    public ServiceInfo getServiceInfoById(String id) {
        if (ServiceStore.getInstance().getServices().containsKey(id)) {
            return ServiceStore.getInstance().getServices().get(id);
        }
        return null;
    }

}
