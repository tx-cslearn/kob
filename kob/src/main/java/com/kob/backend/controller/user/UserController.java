package com.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/all")
    public List<User> getAll(){
        return userMapper.selectList(null);
    }

    @GetMapping("/user/{userId}/")
    public List<User> getuser(@PathVariable int userId){
        QueryWrapper<User> qc = new QueryWrapper<>();
        qc.ge("id",2).le("id",3);
        return userMapper.selectList(qc);
    }

    @GetMapping("/user/{userId}/{userName}/{userpassword}/")
    public String adduser(@PathVariable int userId,
                          @PathVariable String userName,
                          @PathVariable String userpassword){
        PasswordEncoder pse = new BCryptPasswordEncoder();
        String encodepwd = pse.encode(userpassword);
        User user = new User(userId,userName,encodepwd);
        userMapper.insert(user);
        return "add user success";
    }

    @GetMapping("/user/delete/{userId}")
    public String deleteuser(@PathVariable int userId){
        userMapper.deleteById(userId);
        return "delete success";
    }
}
