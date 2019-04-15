package com.liutao.userTest;

import com.liutao.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

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
public class GetClient extends BaseClient {
    private Logger logger = LoggerFactory.getLogger(GetClient.class);

    /**
     * 演示方法： public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
     *           throws RestClientException
     * 参数意义：
     * url：url地址
     * responseType：响应实体类型。
     * uriVariables：url地址参数，如果没有url参数的时候直接传空Map(new HashMap())。
     */
    @Test
    public void testGetForObject_one(){
        String url = "https://api.sdk.tjhaoran.cn/Oauth/Pack/detail?pack_key=0&os=android&client_id=7cfedd98e7a60c12&version=4.0.2";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","rose");
        paramMap.put("age",22);
        Object user = restTemplate.getForObject(url+"?name={name}&age={age}",Object.class,paramMap);
        logger.debug("user:"+user);
    }


    /**
     * 演示方法：public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables)
     *          throws RestClientException
     * 参数意义：
     * url：url地址
     * responseType：响应实体类型。
     * uriVariables：url地址参数，如果url地址上没有参数的，这个参数可以不填。
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
     * 演示方法： public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
     *           throws RestClientException
     * 此方法与 public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws
     * RestClientException方法的使用相同，不同的是这个方法的返回结果为ResponseEntity，可以从返回结果获取响应状态及响应体等信息。
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
     * 演示方法：public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)
     *          throws RestClientException
     * 此方法与 public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws
     * RestClientException方法的使用相同，不同的是这个方法的返回结果为ResponseEntity，可以从返回结果获取响应状态及响应体等信息。
     */
    @Test
    public void testGetFoEntity_two(){
        String url = HOST +"/api-demo/user";
        Object[] arr = new Object[]{"rose", -10};
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(url+"?name={name}&age={age}",User.class,arr);
        disposeResponseEntity(responseEntity);
    }

    /**
     * 演示方法：public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType)
     *          throws RestClientException
     * 参数意义：
     * url：url为URI对象URI uri = new URI("http://localhost:8080/api/demo?name=zhangsan")，如果有url参数需要拼接在地址后面。
     * responseType：响应实体类型。
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
}
