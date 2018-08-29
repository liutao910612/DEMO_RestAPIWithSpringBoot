package com.liutao.restApiController;

import com.liutao.model.User;
import com.liutao.util.ResponseBuilder;
import com.liutao.util.RestfulDataResponse;
import com.liutao.util.RestfulResponse;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户API
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/14  14:54
 * @Modified By:
 */
@RestController
@Api(value = "API of user")
@RequestMapping("/api-demo/")
public class GetDemoController {

    private Logger logger = LoggerFactory.getLogger(GetDemoController.class);

    /**
     * 获取多个用户信息
     * @return
     */
    @GetMapping(value="/users")
    public RestfulResponse getUsers(){
        User user1 = new User("liutao",12,"123");
        User user2 = new User("liubei",12,"123");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        return ResponseBuilder.buildOkDataResponse(userList);
    }

    /**
     * 获取单个用户信息
     * @return
     */
    @GetMapping("user")
    public RestfulDataResponse<User> getUser(
            @RequestParam(value = "name",defaultValue = "liutao")String name,
            @RequestParam("age") int age) {
        logger.debug("name:"+name+",age:"+age);
        User user = new User(name,age,"123");
        return ResponseBuilder.buildOkDataResponse(user);
    }
}
