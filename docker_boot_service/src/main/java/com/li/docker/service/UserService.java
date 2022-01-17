package com.li.docker.service;

import com.li.docker.domain.User;
import com.li.docker.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    private static final String CACHE_KEY_USER = "user:";

    @Resource
    private UserMapper userMapper;


    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Transactional
    public void addUser(User user) {
        int row = userMapper.insertSelective(user);
        if (row > 0) {
            user = userMapper.selectByPrimaryKey(user.getId());
            String key = CACHE_KEY_USER + user.getId();
            redisTemplate.opsForValue().set(key, user);
        }
    }

    public User findUserById(int id) {
        String key = CACHE_KEY_USER + id;
        User user = redisTemplate.opsForValue().get(key);
        if (user == null) {
            user = userMapper.selectByPrimaryKey(id);
            if (user != null) {
                redisTemplate.opsForValue().set(key, user);
            }
        }
        return user;
    }
}
