package fzu.edu.frpc.core.service.impl;

import fzu.edu.frpc.core.entity.RequestInfo;
import fzu.edu.frpc.core.entity.ServiceInfo;
import fzu.edu.frpc.core.exception.ServiceInfoRegisterFailed;
import fzu.edu.frpc.core.io.ServerThread;
import fzu.edu.frpc.core.service.ProviderServiceHelper;
import fzu.edu.frpc.core.store.ServiceStore;
import fzu.edu.frpc.core.until.Constant;
import fzu.edu.frpc.core.until.OkHttpRemoteImpl;
import fzu.edu.frpc.core.until.Remote;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author jinxindong
 * @create 2018-06-11 18:09
 */

public class ProviderServiceHelperImpl implements ProviderServiceHelper {

    @Override
    public boolean startListen() {
        new Thread(new ServerThread()).start();
        return true;
    }

    @Override
    public boolean registry(String interfaceName, String version, String implClassName, String ip) {
        String registryUrl = "http://" + Constant.REGISTER_CENTER_IP + ":" + Constant.REGISTER_CENTER_PORT
                + Constant.REGISTER_CENTER_REGISTER;
        Remote remote = new OkHttpRemoteImpl();
        if (!remote.getRemoteInfo(registryUrl)) {
            throw new ServiceInfoRegisterFailed();
        }
        // 本地缓存一份
        String registerId = interfaceName + "_" + version;
        if (ServiceStore.getInstance().getServices().containsKey(registerId)) {
            ServiceStore.getInstance().getServices().get(registerId).getIps().add(ip);
        } else {
            ServiceInfo serviceInfo = new ServiceInfo();
            serviceInfo.setImplClassName(implClassName);
            serviceInfo.setInterfaceName(interfaceName);
            serviceInfo.setIp(ip);
            serviceInfo.getIps().add(ip);
            serviceInfo.setVersion(version);
            ServiceStore.getInstance().getServices().put(registerId, serviceInfo);
        }
        System.out.println("成功注册服务:[" + interfaceName + "]");
        return true;
    }

    @Override
    public Object call(RequestInfo requestInfo) throws Exception {
        ServiceInfo serviceInfo = ServiceStore.getInstance().getServices().get(requestInfo.getInterfaceName() + "_" + requestInfo.getVersion());
        Class clz = Class.forName(serviceInfo.getImplClassName());
        Method method = null;
        Object result = null;
        if (Objects.nonNull(requestInfo.getParams())) {// 方法调用的参数
            Class[] classes = new Class[requestInfo.getParams().length];// 参数的类型
            Object[] obj = requestInfo.getParams();// 参数
            int i = 0;
            for (Object object : requestInfo.getParams()){
                if(object instanceof Integer){
                    classes[i] = Integer.TYPE;
                }else if(object instanceof Byte){
                    classes[i] = Byte.TYPE;
                }else if(object instanceof Short){
                    classes[i] = Short.TYPE;
                }else if(object instanceof Float){
                    classes[i] = Float.TYPE;
                }else if(object instanceof Double){
                    classes[i] = Double.TYPE;
                }else if(object instanceof Character){
                    classes[i] = Character.TYPE;
                }else if(object instanceof Long){
                    classes[i] = Long.TYPE;
                }else if(object instanceof Boolean){
                    classes[i] = Boolean.TYPE;
                }else {
                    classes[i] = object.getClass();
                }
                i++;
            }
            method = clz.getDeclaredMethod(requestInfo.getMethodName(),classes);
            result = method.invoke(clz.newInstance(),obj);
        }else{
            method = clz.getDeclaredMethod(requestInfo.getMethodName());
            result = method.invoke(clz.newInstance());
        }
        return result;
    }
}
