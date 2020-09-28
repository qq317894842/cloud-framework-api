package net.crisps.cloud;

import com.alibaba.fastjson.JSONObject;
import net.crisps.framework.tac.starter.client.common.utils.PathUtils;
import net.crisps.framework.tac.starter.client.service.RestClient;
import net.crisps.framework.tac.base.utils.MapBuilder;
import net.crisps.framework.tac.starter.strategy.request.ClientRequestProcessor;
import net.crisps.framework.tac.starter.strategy.request.LoadBalancedClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;

public class ClientRequestProcessorTest extends AbstractTest {

    @Autowired
    private Map<String, ClientRequestProcessor> beans;

    @Autowired
    private ClientRequestProcessor clientRequestProcessor;
    @Autowired
    private ClientRequestProcessor httpRequestProcessor;
    @Autowired
    private ClientRequestProcessor loadBalancedProcessor;
    @Autowired
    private LoadBalancedClient loadBalancedClient;
    @Autowired
    private RestClient restClient;

    private String gateWayPath = "https://microuag.dgg188.cn/authplatform";
    private String basePath = "http://tac-authplatform-server";
    private String path = "/api/manager/operate/v1/storage/select/platform_channel_config.do";
    private Map<String, Object> args = MapBuilder.create(String.class, Object.class)
            .push("platformCode", "qds_888")
            .push("channel", "YeDun");

    private Map<String, String> heards = MapBuilder.create(String.class, String.class)
            .push("sysCode", "qds_888")
            .push("X-Req-Client", "ANDROID")
            .push("X-Device-Type", "deviceType")
            .push("X-Device-Code", "deviceCode")
            .push("n-d-env", "dev")
            .push("nonce", "4jadkjakljdakldjakljdakljdkl")
            .push("X-User-Agent", "4b43c3f3-d817-4576-95b1-ad8519a2f14e")
            .push("X-Req-UserId", "7902841307248123906")
            .push("X-Auth-Token", "7961774579524435968");


    public void getClientRequestProcessorBeansTest() {
        Iterator<Map.Entry<String, ClientRequestProcessor>> iterator = beans.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ClientRequestProcessor> next = iterator.next();
            System.err.println(MessageFormat.format("{0} == {1}", next.getKey(), next.getValue()));
        }
    }

    @Test
    public void loadBalancedClientTest() {
        getClientRequestProcessorBeansTest();
        Object obj = loadBalancedClient.doGet(basePath, path, args, heards);
        System.err.println(obj);
    }

    @Test
    public void loadBalancedProcessorTest() {
        getClientRequestProcessorBeansTest();
        Object obj = loadBalancedClient.doGet(PathUtils.getPath(basePath, path), args, heards, JSONObject.class);
        System.err.println(obj);
    }

    @Test
    public void httpRequestProcessorTest() {
        getClientRequestProcessorBeansTest();
        Object obj = httpRequestProcessor.doPostJson(PathUtils.getPath(basePath, path), args, JSONObject.class);
        System.err.println(obj);
    }

    @Test
    public void clientRequestProcessorTest() {
        getClientRequestProcessorBeansTest();
        Object obj = clientRequestProcessor.doPostJson(PathUtils.getPath(basePath, path), args, JSONObject.class);
        System.err.println(obj);
    }

    @Test
    public void restClientTest() {
        getClientRequestProcessorBeansTest();
        Object obj = restClient.doPostJson(PathUtils.getPath(basePath, path), args, JSONObject.class);
        System.err.println(obj);
    }
}
