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
public class PostDemoController {

    private Logger logger = LoggerFactory.getLogger(PostDemoController.class);


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



}
