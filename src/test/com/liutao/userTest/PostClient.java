package com.liutao.userTest;

import com.liutao.model.User;
import com.liutao.util.RestfulDataResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟rest请求客户端
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/14  14:54
 * @Modified By:
 */

public class PostClient extends BaseClient {
    private Logger logger = LoggerFactory.getLogger(PostClient.class);


    /**
     * 演示 public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)的使用
     * 最后一个参数 uriVariables的使用和 public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables)相同，
     * 可以不传，url跟有参数则需要添加。
     *
     */
    @Test
    public void testPostForObject_one(){
        String url = HOST +"/api-demo/json/user?dept={dept}";
        Object[] arr = new Object[]{"finance"};
        int result = restTemplate.postForObject(url,new User("liutao",12,"liutao123"),int.class,arr);
        logger.debug("user:"+result);
    }


    @Test
    public void testPostForObject_two(){
        String url = HOST +"/api-demo/json/user?dept={dept}";
        Object[] arr = new Object[]{"finance"};
        int result = restTemplate.postForObject(url,new User("liutao",12,"liutao123"),int.class,arr);
        logger.debug("user:"+result);
    }

    @Test
    public void testPostForEntity(){
        System.out.println("enter testPostForEntity");
        RestTemplate restTemplate = new RestTemplate();

        /**
         * 注意这里可以在最后面加上地址参数，可以是一个可变参数，也可以是一个Map
         */
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("http://localhost:8888/liutao/v1/create",new User("liutao",12,"liutao123"),Integer.class);
        System.out.println("user:"+ responseEntity.getBody().toString());
        System.out.println("status:"+responseEntity.getStatusCode());
        System.out.println("message:"+responseEntity.getStatusCodeValue());
    }

}
