package com.liutao.userTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 模拟rest请求客户端
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/14  14:54
 * @Modified By:
 */

public class DeleteClient extends BaseClient {
    private Logger logger = LoggerFactory.getLogger(DeleteClient.class);

    @Test
    public void testDelete(){
        System.out.println("enter testPostForEntity");
        RestTemplate restTemplate = new RestTemplate();

        /**
         * 注意这里可以在最后面加上地址参数，可以是一个可变参数，也可以是一个Map
         */
        restTemplate.delete("http://localhost:8888/liutao/v1/delete/liutao");
    }

}
