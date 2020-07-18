package com.chuanchiai.service;

import com.chuanchiai.pojo.User;

/**
 * Created by chuanchiai on 2020/7/18
 */
public interface UserService {

    User queryById(Integer id);

    int updateUser(User user);
}
