package com.liutao.restApiController;

import com.liutao.model.User;
import com.liutao.util.ResponseBuilder;
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
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 获取多个用户信息
     * @param name
     * @return
     */
    @GetMapping(value="/users")
    public RestfulResponse getUsers(@RequestParam(value="name",defaultValue = "liutao") String name){
        logger.info("name from front:"+name);
        User user1 = new User("liutao",12,"123");
        User user2 = new User("liubei",12,"123");
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        return ResponseBuilder.buildOkDataResponse(userList);
    }

    /**
     * 获取单个用户信息
     * @return
     */
    @GetMapping("user")
    public RestfulResponse getUser() {
        User user = new User("liutao",12,"123");
        return ResponseBuilder.buildOkDataResponse(user);
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @PostMapping("user")
    public RestfulResponse addUser(@RequestBody User user){
        logger.debug("user:"+user);
        return ResponseBuilder.buildOkResponse();
    }

    /**
     * 修改用户信息（传入被修改对象的全部）
     * @param user
     * @return
     */
    @PutMapping("user")
    public RestfulResponse updateUser(@RequestBody User user){
        logger.debug("user:"+user);
        return ResponseBuilder.buildOkResponse();
    }

    /**
     * 修改用户名（传入被修改对象的部分信息）
     * @param id
     * @param username
     * @return
     */
    @PatchMapping("user/{id}")
    public RestfulResponse updateUserName(
            @PathVariable("id")String id,
            @RequestParam("username") String username
    ){
        logger.debug("id:"+id);
        logger.debug("username:"+username);
        return ResponseBuilder.buildOkResponse();
    }


}
