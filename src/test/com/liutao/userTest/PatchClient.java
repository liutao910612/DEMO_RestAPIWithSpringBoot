package com.liutao.userTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟rest请求客户端
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/14  14:54
 * @Modified By:
 */

public class PatchClient extends BaseClient {

    Logger logger = LoggerFactory.getLogger(PatchClient.class);

    /**
     * 演示方法：public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType,
     * 			Object... uriVariables) throws RestClientException
     * 参数意义：
     * url：url地址
     * request：请求实体对象
     * uriVariables：url地址参数，如果url地址上没有参数的，这个参数可以不填，的使用和 public <T> T getForObject(String url,
     * Class<T> responseType,Object... uriVariables)相同。
     *
     * 类似方法1：public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType,
     * 			Map<String, ?> uriVariables) throws RestClientException
     * 方法和“演示方法”基本相同，使用也相同，不同的是此方法最后一个url参数的传值使用的是Map,因此如果没有url参数的时候直接传
     * 空Map(new HashMap())。
     *
     * 类似方法2：public <T> T patchForObject(URI url, @Nullable Object request, Class<T> responseType)
     * throws RestClientException
     * 这个方法和“演示方法”使用基本相同，不同的是url。例如第一个参数为：URI uri = new URI("http://localhost:8080/api/demo");
     *
     */
    @Test
    public void testPatch(){
        String url = HOST +"/api-demo/user/1212?name={name}";
        Object[] arr = new Object[]{"rose"};
        Integer result = restTemplate.patchForObject(url,null,Integer.class,arr);
        logger.debug("result:"+result);
    }

}
