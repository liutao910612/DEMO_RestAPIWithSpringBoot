package com.liutao.userTest;

import com.liutao.application.Application;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author: LIUTAO
 * @Date: Created in 2018/8/28  20:24
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseClient extends TestCase {
    @Autowired
    protected RestTemplate restTemplate;
    protected static final String HOST = "http://localhost:8888";
}
