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


    @Test
    public void testPostForObject(){
        System.out.println("enter testPostForObject");
        RestTemplate restTemplate = new RestTemplate();

        /**
         * 注意这里可以在最后面加上地址参数，可以是一个可变参数，也可以是一个Map
         */
        User user = restTemplate.postForObject("http://localhost:8888/liutao/v1/create",new User("liutao",12,"liutao123"),User.class);
        System.out.println("user:"+user);
    }

    @Test
    public void testPostForEntity(){
        System.out.println("enter testPostForEntity");
        RestTemplate restTemplate = new RestTemplate();

        /**
         * 注意这里可以在最后面加上地址参数，可以是一个可变参数，也可以是一个Map
         */
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:8888/liutao/v1/create",new User("liutao",12,"liutao123"),User.class);
        System.out.println("user:"+((User)responseEntity.getBody()).toString());
        System.out.println("status:"+responseEntity.getStatusCode());
        System.out.println("message:"+responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        System.out.println("enter testPostForEntity");
        RestTemplate restTemplate = new RestTemplate();

        /**
         * 注意这里可以在最后面加上地址参数，可以是一个可变参数，也可以是一个Map
         */
        restTemplate.delete("http://localhost:8888/liutao/v1/delete/liutao");
    }

}
