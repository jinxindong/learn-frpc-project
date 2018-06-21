package fzu.edu.frpc.core.openserver.impl;

import fzu.edu.frpc.core.entity.RequestInfo;
import fzu.edu.frpc.core.exception.ServiceInfoCallException;
import fzu.edu.frpc.core.exception.ServiceInfoIllgeal;
import fzu.edu.frpc.core.exception.ServiceInfoNotFound;
import fzu.edu.frpc.core.exception.ServiceInfoRemoteCallException;
import fzu.edu.frpc.core.openserver.FRPCConsumer;
import fzu.edu.frpc.core.service.ConsumerServiceHelper;
import fzu.edu.frpc.core.service.impl.ConsumerServiceHelperImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

/**
 * @author jinxindong
 * @create 2018-06-19 16:53
 */

public class FRPCConsumerImpl implements FRPCConsumer {
    private String interfaceName;
    private String version;
    private ConsumerServiceHelper consumerServiceHelper;

    public FRPCConsumerImpl() {
        consumerServiceHelper = new ConsumerServiceHelperImpl();
    }

    @Override
    public Object serviceConsumer(String methodName, Object[] params) throws Exception {
        if (Strings.isBlank(interfaceName) || Strings.isBlank(version)) {
            throw new ServiceInfoIllgeal();
        }
        String serviceId = interfaceName + "_" + version;
        Set<String> ips = consumerServiceHelper.getServiceIPsByID(serviceId);
        if (CollectionUtils.isEmpty(ips)) {
            throw new ServiceInfoNotFound();
        }
        // 进行路由 选定唯一ip
        String ip = consumerServiceHelper.getIp(serviceId, methodName, params, ips);
        // 组装合法的请求对象
       RequestInfo requestInfo = consumerServiceHelper.getRequestInfo(interfaceName, version, methodName, params);

        Object result = null;
        try {
            // 可扩展多种调用方式  序列化后发送 和 发送回接收结果
            result = consumerServiceHelper.sendData(ip,requestInfo);
        }catch (Exception e){
            throw new ServiceInfoCallException();
        }
        if(Objects.isNull(result)){
            throw new ServiceInfoRemoteCallException();
        }
        return result;
    }
}
