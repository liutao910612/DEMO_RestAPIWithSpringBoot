package com.liutao.userTest;

import com.liutao.application.Application;
import com.liutao.model.User;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<String,String>();
        headers.add("Accept","application/json");
        headers.add("Content-Type","application/json");

        HttpEntity<User> requestEntity = new HttpEntity(new User("dasf",12,"liutao123"),headers);
        ResponseEntity<Result> responseEntity = restTemplate.exchange("http://localhost:8888/liutao/v1/getResult",
                HttpMethod.POST,requestEntity,Result.class);
        System.out.println(responseEntity.getBody());
    }


}
