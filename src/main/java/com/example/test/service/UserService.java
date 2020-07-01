package com.example.test.service;

import com.example.test.entity.Bean;
import com.example.test.entity.User;
import com.example.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    public List<User> userList(){
        return userMapper.getAllUser();
    }

    public User queryUserById(Integer userId){return userMapper.queryUserById(userId);}

    public int insert(User user){
        return userMapper.InsertUser(user);
    }

    public int delete(Integer id){
        return userMapper.delUserById(id);
    }

    public int update(User user){
        return userMapper.UpdateUser(user);
    }

    public User getById(Integer id){
        return userMapper.getUserById(id);
    }

}
