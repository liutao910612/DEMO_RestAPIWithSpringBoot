package com.liutao.restApiController;

import com.liutao.util.ResponseBuilder;
import com.liutao.util.RestfulResponse;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api-demo/")
public class PatchDemoController {

    private Logger logger = LoggerFactory.getLogger(PatchDemoController.class);

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
