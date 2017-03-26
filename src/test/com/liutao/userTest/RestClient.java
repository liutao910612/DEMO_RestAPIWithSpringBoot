package com.liutao.userTest;

import com.liutao.application.Application;
import com.liutao.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liutao on 2017/3/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RestClient {

    @Test
    public void testGetForObject(){
        RestTemplate restTemplate = new RestTemplate();
        /**
         * 注意：这里的最后一个参数是地址参数，可以有也可以没有
         */
        User user = restTemplate.getForObject("http://localhost:8888/liutao/v1/getUser/zhangfei",User.class,"zhangfei");
        System.out.println(user.toString());
    }

    @Test
    public void testGetForObject2(){
        RestTemplate restTemplate = new RestTemplate();
        /**
         * 注意：这里的最后一个参数是地址参数，可以有也可以没有
         */
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://localhost:8888/liutao/v1/getUser/{name}",User.class,"zhangfei");
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody().toString());
    }

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

    @Test
    public void testExchange(){
        System.out.println("enter testExchange");
        RestTemplate restTemplate = new RestTemplate();
        /**
         * 如下方法是exchange重载方法的一种，参数意义如下：
         * var1：URL地址
         * var2：请求方法
         * var3：请求体设置
         * var4：返回的响应体body类型
         * var5：地址参数
         * <T> ResponseEntity<T> exchange(String var1, HttpMethod var2, HttpEntity<?> var3, Class<T> var4, Object... var5)
         * throws RestClientException;
         */
//        ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:8888/liutao/v1/getUser/{name}",HttpMethod.GET,null,User.class,"zhangfei");
//        System.out.println(responseEntity.getBody().toString());

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<String,String>();
        headers.add("Accept","application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
        ResponseEntity<User> responseEntity2 = restTemplate.exchange("http://localhost:8888/liutao/v1/getUser/{name}",HttpMethod.GET,requestEntity,User.class,"zhangfei");
        System.out.println(responseEntity2.getBody());
    }


}
