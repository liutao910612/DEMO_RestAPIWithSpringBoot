package com.liutao.userTest;

import com.liutao.application.Application;
import com.liutao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * Created by liutao on 2017/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserInfoTest(){
        System.out.println(userService.getUserInfo());
    }

}
