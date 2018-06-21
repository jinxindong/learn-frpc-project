package fzu.edu.frpc.center.server;

import com.google.gson.Gson;
import fzu.edu.frpc.center.centerService.CenterService;
import fzu.edu.frpc.center.entity.ServiceInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

/**
 * 提供服务注册、服务地址列表查询、服务信息查询  根据服务的全限定名查询
 *
 * @author jinxindong
 * @create 2018-06-11 13:23
 */
@RestController
@Api("服务注册中心接口")
public class ServerController {

    @Autowired
    private CenterService centerService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation("注册服务到注册中心")
    public String registerService(@RequestBody ServiceInfo serviceInfo) {
        Gson gson = new Gson();
        return gson.toJson(centerService.register(serviceInfo));
    }

    @RequestMapping(value = "/getServiceIPsById", method = RequestMethod.GET)
    @ApiOperation("根据服务id，查询服务的地址列表")
    public String getServiceIPsById(@RequestParam(value = "id") String id) {
        Set<String> ips = centerService.getServiceIPsById(id);
        if(CollectionUtils.isEmpty(ips)){
            return "";
        }
        Gson gson = new Gson();
        return gson.toJson(ips);
    }

    @RequestMapping(value = "/getServiceInfoById", method = RequestMethod.GET)
    @ApiOperation("根据服务id，查询服务的信息")
    public String getServiceInfoById(@RequestParam(value = "id") String id) {
        ServiceInfo serviceInfo = centerService.getServiceInfoById(id);
        if(Objects.isNull(serviceInfo)){
            return "";
        }
        Gson gson = new Gson();
        return gson.toJson(serviceInfo);
    }
}
