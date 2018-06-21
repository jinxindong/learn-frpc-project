package fzu.edu.frpc.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jinxindong
 * @create 2018-06-11 17:13
 */
@Data
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 6434907240763251152L;
    private String interfaceName;
    private String version;
    private String methodName;
    private Object[] params; // 方法调用的参数

}
