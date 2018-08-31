package com.liutao.restApiController;

import com.liutao.model.User;
import com.liutao.util.ResponseBuilder;
import com.liutao.util.RestfulResponse;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 用户API
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/14  14:54
 * @Modified By:
 */
@RestController
@Api(value = "API of user")
@RequestMapping("/api-demo")
public class PostDemoController {

    private Logger logger = LoggerFactory.getLogger(PostDemoController.class);

    /**
     * 添加用户信息
     * 请求参数支持json
     * @param user
     * @param dept
     * @return
     */
    @PostMapping("/json/user")
    public int addUserToSpecialDepart(
            @RequestParam("dept") String dept,
            @RequestBody User user
    ){
        logger.debug("dept:"+dept+",user:"+user);
        return 1;
    }

    /**
     * 添加用户信息
     * 请求参数支持表单数据
     * @param user
     * @return
     */
    @PostMapping("/form/user")
    public int addUserByForm(@ModelAttribute User user){
        logger.debug("user:"+user);
        return 1;
    }
}
