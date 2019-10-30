package com.example.service;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.model.UserExample;
import com.example.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by codedrinker on 2019/5/23.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {

      //  List<User> users = userMapper.findByAccountID(user.getAccountId());
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {

            // 插入
            user.setGmtCreate(TimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(TimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            updateUser.setBio(user.getBio());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser,userExample);
           // userMapper.updateUser(updateUser);
        }
    }


    public Object checkUsername(String username) {
        User user = userMapper.checkByUsername(username);
        JSONObject jo = new JSONObject();
        if (user!=null){
            jo.put("valid",false);
            return jo;
        }
        jo.put("valid",true);
        return jo;
    }
}
