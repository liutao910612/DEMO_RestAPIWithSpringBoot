package com.liutao.userTest;

import com.liutao.model.User;
import org.junit.Test;

/**
 * 模拟rest请求客户端
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/14  14:54
 * @Modified By:
 */

public class PutClient extends BaseClient {

    /**
     * 演示方法： public void put(String url, @Nullable Object request, Object... uriVariables)
     * 			throws RestClientException
     * 参数意义：
     * url：url地址
     * request：请求实体对象
     * uriVariables：url地址参数，如果url地址上没有参数的，这个参数可以不填，的使用和 public <T> T getForObject(String url,
     * Class<T> responseType,Object... uriVariables)相同。
     *
     * RestTemplate 的其余两个put请求方法和前面的get和post的对应方法使用类似，我们可以查看源码和参考前面的get、post方法的相应方法
     * 进行学习使用。
     *
     * 注意：这里的put方法没有获取任何响应，那么如果我们要获取响应咋个办呢？那就只有直用exchange方法来实现put请求。
     *
     */
    @Test
    public void testPut(){
        String url = HOST +"/api-demo/user";
        User user = new User("liutao",12,"liutao123");
        restTemplate.put(url,user);
    }

}
