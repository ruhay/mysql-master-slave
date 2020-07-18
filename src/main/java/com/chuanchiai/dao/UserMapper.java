package com.chuanchiai.dao;

import com.chuanchiai.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Created by chuanchiai on 2020/7/19
 */
@Repository
public interface UserMapper {
    User queryById(Integer id);

    int updateUser(User user);
}
