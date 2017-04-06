package com.liutao.restApiController;

import com.liutao.domain.Book;
import com.liutao.domain.Error;
import com.liutao.domain.Result;
import com.liutao.domain.User;
import com.liutao.exception.UserNotFoundException;
import com.liutao.service.UserService;
import com.wordnik.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 2017/3/22.
 */
//为控制器默认设置消息转换
@RestController
@Api(value = "test")
@RequestMapping("/liutao/v1")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="/getUserList",method=RequestMethod.GET,produces = "application/json")
    public List<User> getUserList(@RequestParam(value="name",defaultValue = "liutao") String name){
        logger.info("name from front:"+name);
        User user1 = new User("liutao",12,"123");
        User user2 = new User("liubei",12,"123");
        User user3 = new User("liubang",12,"123");
        User user4 = new User("liuche",12,"123");

        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        return userList;
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    //为控制器默认设置消息转换
    @ResponseBody
    public User getUserInfo() {
        User user = userService.getUserInfo();
        if(user!=null){
            logger.info("user.getAge():"+user.getAge());
        }
        return user;
    }

    /**
     * 在请求体中接收资源状态
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveUser",method=RequestMethod.POST,consumes = "application/json")
    public User saveUser(@RequestBody User user){
        logger.debug("enter saveUser");
        logger.debug("name:"+user.getName());
        return user;
    }

    /**
     * 发送错误信息到客户端
     */
    @RequestMapping(value="/findUser/{name}",method = RequestMethod.GET)
    public User findUser(@PathVariable String name){
        User user = null;
        if (user == null){
            logger.info("enter findUser");
            throw  new UserNotFoundException(name);
        }
        return user;
    }

    /**
     * 异常处理类
     * @param userNotFoundException
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error userNotFound(UserNotFoundException userNotFoundException){
        String name = userNotFoundException.getName();
        Error error = new Error(4,"User ["+name+"] not found");
        return error;
    }

    /**
     * 在响应中设置头部信息
     * @param user
     * @param ucb
     * @return
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user, UriComponentsBuilder ucb){
        /**
         * 保存代码略
         */
        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/liutao/v1/add/")
                .path(user.getName())
                .build().toUri();
        httpHeaders.setLocation(locationUri);
        return new ResponseEntity<User>(user,httpHeaders,HttpStatus.CREATED);
    }


    /**
     * 以下的API是为了测试RestClient而编写的
     */

    /**
     * 演示读取（Read）
     * @param name
     * @return
     */
    @RequestMapping(value="/getUser/{name}",method = RequestMethod.GET)
    public User getUser(@PathVariable String name){
        logger.info("enter getUser");
        return new User(name,12,"liutao123");
    }

    /**
     * 演示修改（Update）
     * @param user
     */
    @RequestMapping(value="/updateUser",method = RequestMethod.PUT,consumes = "application/json")
    public void putUser(@RequestBody User user){
        logger.info("enter put");
        logger.info("user:"+user);
    }

    /**
     * 演示创建（Create）
      * @param user
     * @return
     */
    @RequestMapping(value = "/create" ,method = RequestMethod.POST,consumes = "application/json")
    public User postUser(@RequestBody User user){
        logger.info("enter post");
        return user;
    }

    @RequestMapping(value = "/delete/{name}" ,method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String name){
        logger.info("enter delete");
        logger.info("name:"+name);
    }

    /**
     * 发送错误信息到客户端
     */
    @RequestMapping(value="/getBook",method = RequestMethod.GET)
    public Book getBook(){
        return  new Book("read and black","liutao");
    }

    @RequestMapping(value="/getResult",method = RequestMethod.POST,consumes = "application/json")
    public @ResponseBody Result getResult(@RequestBody User user){
        List<String> data = new ArrayList<>();
        data.add("2017040601");
        data.add("2017040602");
        data.add("2017040603");
        return new Result("999","上传成功",data);
    }
}
