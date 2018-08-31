package com.liutao.userTest;

import com.liutao.application.Application;
import com.liutao.model.User;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ExchangeClient extends BaseClient {

    Logger logger = LoggerFactory.getLogger(ExchangeClient.class);

    /**
     * 演示方法：public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
     *                        @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
     * 			throws RestClientException
     * 参数意义：
     * url：url地址
     * method：http请求方法，通过HttpMethod枚举类获取
     * requestEntity：请求实体，可以包含请求头和请求体的信息
     * responseType：响应类型
     * uriVariables：url地址参数，如果url地址上没有参数的，这个参数可以不填
     * 此方法可以从返回结果ResponseEntity中获取到响应状态码以及响应头和响应体等信息。
     *
     * 类似方法1：public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
     * 			@Nullable HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables)
     * 			throws RestClientException
     * 方法和“演示方法”基本相同，使用也相同，不同的是此方法最后一个url参数的传值使用的是Map,因此如果没有url参数的时候直接传
     * 空Map(new HashMap())。
     *
     * 类似方法2：public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
     * 			Class<T> responseType) throws RestClientException
     * 方法和“演示方法”基本相同，使用也相同，不同的事此方法的url参数是一个URI对象，并且没有最后一个参数，因此如果url有参数的时候，
     * 需要我们直接拼接在url后面。例如：URI uri = new URI("http://localhost:8080/api/demo?name=zhangsan")
     *
     */
    @Test
    public void testExchange_one(){

        //封装请求头
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<String,String>();
        headers.add("Accept","application/json");
        headers.add("Content-Type","application/json");

        //封装请求内容
        User user = new User("dasf",12,"liutao123");
        HttpEntity<User> requestEntity = new HttpEntity(user,headers);

        String url = HOST +"/api-demo/user";
        ResponseEntity<Integer> responseEntity = restTemplate.exchange(url,
                HttpMethod.PUT,requestEntity,Integer.class);
        logger.debug("responseEntity:"+responseEntity);
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
