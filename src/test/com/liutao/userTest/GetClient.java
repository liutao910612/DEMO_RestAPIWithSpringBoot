package com.liutao.userTest;

import com.liutao.model.User;
import org.junit.Test;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 模拟rest请求客户端
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/14  14:54
 * @Modified By:
 */

public class GetClient extends BaseClient {
    private Logger logger = LoggerFactory.getLogger(GetClient.class);

    /**
     * 演示 public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) 方法的使用
     */
    @Test
    public void testGetForObject_one(){
        String url = HOST +"/api-demo/user";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","rose");
        paramMap.put("age",22);
        User user = restTemplate.getForObject(url+"?name={name}&age={age}",User.class,paramMap);
        logger.debug("user:"+user);
    }


    /**
     * 演示 public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables)方法的使用
     */
    @Test
    public void testGetForObject_two(){
        String url = HOST +"/api-demo/user";
        Object[] arr = new Object[]{"rose", 22};

        /**
         * 注意这种调用方式针对URL中没有参数的情况可以不传入最后的一个参数
         */
        User user = restTemplate.getForObject(url+"?name={name}&age={age}",User.class,arr);
        logger.debug("user:"+user);
    }

    /**
     * 演示 public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)方法的使用
     */
    @Test
    public void testGetFoEntity_one(){
        String url = HOST +"/api-demo/user";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","rose");
        paramMap.put("age",22);
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(url+"?name={name}&age={age}",User.class,paramMap);
        disposeResponseEntity(responseEntity);
    }


    /**
     * 演示 public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)方法的使用
     */
    @Test
    public void testGetFoEntity_two(){
        String url = HOST +"/api-demo/user";
        Object[] arr = new Object[]{"rose", -10};
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(url+"?name={name}&age={age}",User.class,arr);
        disposeResponseEntity(responseEntity);
    }

    /**
     * 演示 public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType)方法的使用
     */
    @Test
    public void testGetFoEntity_three(){
        String url = HOST +"/api-demo/user";
        url = url+"?name=rose&age=22";
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(uri,User.class);

        disposeResponseEntity(responseEntity);
    }

    /**
     * 处理响应responseEntity
     * @param responseEntity
     */
    private void disposeResponseEntity(ResponseEntity<User> responseEntity) {
        //获取响应状态code
        int code = responseEntity.getStatusCodeValue();
        logger.debug("httpstatus code:"+code);

        //获取响应头相关信息
        if(code > 1000){
            String errorMessage = String.valueOf(responseEntity.getHeaders().get("Error-Message"));
            logger.debug("Error-Message:"+errorMessage);
        }

        //获取响应体
        User user = responseEntity.getBody();
        logger.debug("user:"+user);
    }

    /**
     * 演示将token放置再请求头中传递至后台进行验证
     *
     * 通过exchange方法提交get请求，并在参数中通过指定requestHeaders来设置请求头参数
     */
    @Test
    public void howToSendToken(){
        String url = HOST +"/api-demo/finance/user?name={name}";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","rose");
        //设置请求头数据
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token",UUID.randomUUID().toString());
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<User> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,requestEntity,User.class,paramMap);
        logger.debug(responseEntity.getBody().toString());
    }
}
