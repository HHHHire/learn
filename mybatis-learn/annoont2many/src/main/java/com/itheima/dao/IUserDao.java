package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "birthday", property = "userBirthday"),
            @Result(column = "id", property = "accounts", many = @Many(select = "com.itheima.dao.IAccountDao.findById", fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    @ResultMap("userMap")
    void findById(Integer id);

    @Select("select * from user where username like '%${value}%'")
    @ResultMap("userMap")
    List<User> findByName(String username);

    @Select("select count(*) from user")
    @ResultMap("userMap")
    Integer findTotalUser();
}
