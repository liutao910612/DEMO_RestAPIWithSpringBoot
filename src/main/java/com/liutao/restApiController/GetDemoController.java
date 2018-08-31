package com.liutao.restApiController;

import com.liutao.enums.SelfHttpStatus;
import com.liutao.model.User;
import com.liutao.util.CommonUtil;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public List<User> getUsers(){
        User user1 = new User("liutao",12,"123");
        User user2 = new User("liubei",12,"123");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        return userList;
    }

    /**
     * 获取单个用户信息
     * 将错误码和错误信息存放在header中进行传递
     * @return
     */
    @GetMapping("/user")
    public User getUser(
            @RequestParam(value = "name",defaultValue = "liutao")String name,
            @RequestParam(value = "age",defaultValue = "10") int age) {

        //验证传入的参数的合法性，如果不合法，返回错误信息
        if(age < 0){
            CommonUtil.buildErrorResponse(SelfHttpStatus.PARAMS_NOT_COMPLETE);
            return null;
        }

        logger.debug("name:"+name+",age:"+age);
        User user = new User(name,age,"123");
        return user;
    }

    /**
     * 获取某个部门下的某个用户
     * 这里针对部门的限制使用了路径参数，使用PathVariable标签来获取部门信息
     *
     * @param dept
     * @param name
     * @return
     */
    @GetMapping("/{dept}/user")
    public User getUserOfDept(
            @PathVariable("dept") String dept,
            @RequestParam(value = "name")String name,
            HttpServletRequest request
    ){

        //获取到请求头的token，然后进行相关的认证逻辑
        String token = request.getHeader("token");
        logger.debug("token:"+token);

        logger.debug("dept:"+dept+",name:"+name);
        User user = new User(name,dept);
        return user;
    }


}
