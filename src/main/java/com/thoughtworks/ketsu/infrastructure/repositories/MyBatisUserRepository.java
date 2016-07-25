package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;


public class MyBatisUserRepository implements UserRepository {
    @Inject
    UserMapper userMapper;

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(userMapper.findById(id));
    }

    @Override
    public User createUser(Map<String, Object> userInfo) {
        userMapper.createUser(userInfo);
        return userMapper.findById(Integer.valueOf(userInfo.get("id").toString()));
    }

}
