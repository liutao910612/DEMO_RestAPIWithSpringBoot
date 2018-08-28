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

public class PutClient extends BaseClient {
    private Logger logger = LoggerFactory.getLogger(PutClient.class);

    @Test
    public void testPut(){
        System.out.println("enter testPut");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User("liutao",12,"liutao123");
        Map<String,Object> requestMap = new HashMap<String,Object>();
        requestMap.put("name",user.getName());
        requestMap.put("age",user.getAge());
        requestMap.put("password",user.getPassword());
        /**
         * 注意一下这两种方法都可以调用PUT请求，第一个传入参数是以Map形式，
         * 第二个传入参数是以可变参数形式
         * 注意：这里的最后一个参数是地址参数，可以有也可以没有，因为是地址参数
         */
//        restTemplate.put("http://localhost:8888/liutao/v1/updateUser",user,requestMap);
        restTemplate.put("http://localhost:8888/liutao/v1/updateUser",user,user.getName(),user.getAge(),user.getPassword());
    }

}
