package fzu.edu.frpc.core.openserver.impl;

import fzu.edu.frpc.core.exception.ServiceInfoListenFailed;
import fzu.edu.frpc.core.exception.ServiceInfoNotComplete;
import fzu.edu.frpc.core.openserver.FRPCProvider;
import fzu.edu.frpc.core.service.ProviderServiceHelper;
import org.springframework.util.StringUtils;

/**
 * @author jinxindong
 * @create 2018-06-11 18:12
 */

public class FRPCProviderImpl implements FRPCProvider {
    private String interfaceName;
    private String version;
    private String implClassName;
    private String ip;

    private static boolean isListened = false;

    private ProviderServiceHelper providerServiceHelper;

    public FRPCProviderImpl(ProviderServiceHelper providerServiceHelper) {
        this.providerServiceHelper = providerServiceHelper;
    }

    @Override
    public boolean servicePublish() {
        parameterCheck();
        synchronized (FRPCProviderImpl.class) {
            // 注册到用户中心
            providerServiceHelper.registry(interfaceName, version, implClassName, ip);
            if (!isListened) {
                if (providerServiceHelper.startListen()) {
                    isListened = true;
                } else {
                    throw new ServiceInfoListenFailed();
                }
            }
        }

        return true;

    }

    private void parameterCheck() {
        if (StringUtils.isEmpty(interfaceName) ||
                StringUtils.isEmpty(version) ||
                StringUtils.isEmpty(implClassName) ||
                StringUtils.isEmpty(ip)) {
            throw new ServiceInfoNotComplete();
        }
    }


}
