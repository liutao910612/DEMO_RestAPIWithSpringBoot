package com.liutao.userTest;

import com.liutao.model.User;
import com.liutao.util.RestfulDataResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    @Test
    public void testGetForObject_one(){
        String url = HOST +"/api-demo/user";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","rose");
        paramMap.put("age",22);
        RestfulDataResponse<User> restfulResponse = restTemplate.getForObject(url+"?name={name}&age={age}",RestfulDataResponse.class,paramMap);
        logger.debug("restfulResponse:"+restfulResponse);
    }

    @Test
    public void testGetForObject_two(){
        String url = HOST +"/api-demo/user";
        Object[] arr = new Object[]{"rose", 22};

        /**
         * 注意这种调用方式针对URL中没有参数的情况可以不传入最后的一个参数
         */
        RestfulDataResponse<User> restfulResponse = restTemplate.getForObject(url+"?name={name}&age={age}",RestfulDataResponse.class,arr);
        logger.debug("restfulResponse:"+restfulResponse);
    }

    @Test
    public void testGetFoEntity_one(){
        String url = HOST +"/api-demo/user";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","rose");
        paramMap.put("age",22);
        ResponseEntity<RestfulDataResponse> responseEntity = restTemplate.getForEntity(url+"?name={name}&age={age}",RestfulDataResponse.class,paramMap);
        disposeResponseEntity(responseEntity);
    }



    @Test
    public void testGetFoEntity_two(){
        String url = HOST +"/api-demo/user";
        Object[] arr = new Object[]{"rose", 22};
        ResponseEntity<RestfulDataResponse> responseEntity = restTemplate.getForEntity(url+"?name={name}&age={age}",RestfulDataResponse.class,arr);
        disposeResponseEntity(responseEntity);
    }

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
        ResponseEntity<RestfulDataResponse> responseEntity = restTemplate.getForEntity(uri,RestfulDataResponse.class);

        disposeResponseEntity(responseEntity);
    }

    /**
     * 处理响应responseEntity
     * @param responseEntity
     */
    private void disposeResponseEntity(ResponseEntity<RestfulDataResponse> responseEntity) {
        //获取响应状态code
        HttpStatus httpStatus = responseEntity.getStatusCode();
        logger.debug("httpstatus code:"+httpStatus.value());

        //获取响应体
        RestfulDataResponse restfulDataResponse = responseEntity.getBody();
        logger.debug("restfulDataResponse:"+restfulDataResponse);
    }
}
