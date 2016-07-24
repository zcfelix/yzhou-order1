package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;

import javax.ws.rs.core.Context;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {

    User createUser(Map<String, Object> userInfo);

    User findById(int id);


    //Optional<User> ofId(UserId id);

    //User findUserByName(String userName);
}
