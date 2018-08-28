package com.example.hello;

//import com.netflix.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangXu
 * @date 2018/8/28 下午11:09
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    // TODO: by ZhangXu 2018/8/28 下午11:26 :: 如何将一个方法映射到多个URL地址？
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        // TODO: by ZhangXu 2018/8/28 下午11:15 :: what is serviceId ?
        ServiceInstance instance = client.getInstances("hello-service").get(0);
        logger.warn("/hello,host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "hello world";
    }
}
