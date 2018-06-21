package fzu.edu.frpc.core.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jinxindong
 * @create 2018-06-11 17:11
 */
@Data
public class ServiceInfo {
    private String interfaceName;// 服务对应接口的名字
    private String version;
    private String implClassName; // 实现该接口的实现类的名字
    private String ip;// 某一次注册服务的地址
    private Set<String> ips = new HashSet<>();// 服务地址列表
}
