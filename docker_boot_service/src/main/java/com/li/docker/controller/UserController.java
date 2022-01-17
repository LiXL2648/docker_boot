package com.li.docker.controller;

import com.li.docker.domain.User;
import com.li.docker.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
@Api(value = "用户User接口")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("新增用户")
    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @ApiOperation("根据Id查找用户")
    @GetMapping("/user/{id}")
    public User getOneUser(@PathVariable("id") Integer id) {
        return userService.findUserById(id);
    }
}
