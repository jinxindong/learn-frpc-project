package fzu.edu.frpc.center.store;

import fzu.edu.frpc.center.entity.ServiceInfo;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jinxindong
 * @create 2018-06-11 13:08
 */
@Data
public class ServiceStore {
    private static ServiceStore serviceStore = new ServiceStore();
    private Map<String, ServiceInfo> services = new ConcurrentHashMap<>();

    private ServiceStore() {
        services = new ConcurrentHashMap<>();
    }

    public static ServiceStore getInstance() {
        return serviceStore;
    }
}
