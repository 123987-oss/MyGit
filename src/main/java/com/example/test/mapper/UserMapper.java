package com.example.test.mapper;

import com.example.test.entity.User;
import com.example.test.entity.Bean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> getAllUser();

    @Select("select * from user where id=#{id}")
    public User getUserById(Integer id);

    @Delete("delete from user where id=#{id}")
    public int delUserById(Integer id);

    @Update("update user set name=#{name},phone=#{phone},gid=#{gid},message=#{message},time=#{time},service=#{service} where id=#{id}")
    public int UpdateUser(User user);

    @Select("select * from user where id = ${userid}")
    User queryUserById(Integer userId);

    @Options (useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(name,phone,gid,message,time,service) values (#{name},#{phone},#{gid},#{message},#{time},#{service})")
    public int InsertUser(User user);

}
