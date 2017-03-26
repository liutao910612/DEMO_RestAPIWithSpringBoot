package com.liutao.service;

import com.liutao.mapper.UserMapper;
import com.liutao.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public User getUserInfo(){
        User user=userMapper.findUserInfo();
        return user;
    }

}
