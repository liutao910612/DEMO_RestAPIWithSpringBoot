package com.liutao.userTest;

import com.liutao.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
     * 演示 public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType,
     * Object... uriVariables)最后一个参数 uriVariables的使用和 public <T> T getForObject(String url, Class<T> responseType,
     * Object... uriVariables)相同，可以不传，url有参数则需要添加。
     *
     * public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType,
     * Map<String, ?> uriVariables)方法的
     * 使用和此方法基本相同，不同的是本方法的最后一个参数必须存在。
     *
     * public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType)方法，仅仅需要将第一
     * 个参数换作URI即可。例如第一个参数为：URI uri = new URI("http://localhost:8080/api/demo");
     *
     */
    @Test
    public void testPostForObject_one(){
        String url = HOST +"/api-demo/json/user?dept={dept}";
        Object[] arr = new Object[]{"finance"};
        int result = restTemplate.postForObject(url,new User("liutao",12,"liutao123"),int.class,arr);
        logger.debug("result:"+result);
    }

    /**
     * 演示 public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,Class<T> responseType,
     * Object... uriVariables)。同样，当url中没有参数的时候，最后一个参数uriVariables可以不填。
     *
     * public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,Class<T> responseType,
     * Map<String, ?> uriVariables) 方法的使用和此方法基本相同，不同的是本方法的最后一个参数必须存在。
     *
     * public <T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType)方法，
     * 仅仅需要将第一个参数换作URI即可。例如第一个参数为：URI uri = new URI("http://localhost:8080/api/demo");
     */
    @Test
    public void testPostForEntity(){
        String url = HOST +"/api-demo/json/user?dept={dept}";
        Object[] arr = new Object[]{"finance"};
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(url,new User("liutao",12,
                        "liutao123"),
                Integer.class,arr);
        logger.debug("responseEntity:"+responseEntity);
    }

    /**
     * 演示数据传递以form表单的格式进行
     */
    @Test
    public void testPostForm(){
        String url = HOST +"/api-demo/form/user";

        //设置请求数据的格式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //封装参数
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("name", "liutao");
        params.add("age", "12");
        params.add("password", "liutao123");

        //封装请求内容
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params,headers);
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(url,requestEntity,Integer.class);
        logger.debug("responseEntity:"+responseEntity);
    }
}
