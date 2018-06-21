package fzu.edu.frpc.core.until;

import java.util.Map;

/**
 * @author jinxindong
 * @create 2018-06-11 19:10
 */

public interface Remote {
    public boolean getRemoteInfo(String url);

    public boolean postRemoteInfo(String url, Map<String, String> params);
}
