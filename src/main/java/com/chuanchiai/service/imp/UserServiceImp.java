package com.chuanchiai.service.imp;

import com.chuanchiai.dao.UserMapper;
import com.chuanchiai.dynamicRouting.DataSourceSelector;
import com.chuanchiai.enums.DataSourceEnum;
import com.chuanchiai.pojo.User;
import com.chuanchiai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chuanchiai on 2020/7/19
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @DataSourceSelector(name = DataSourceEnum.SLAVE)
    @Override
    public User queryById(Integer id) {
        return userMapper.queryById(id);
    }

    @DataSourceSelector(name = DataSourceEnum.MASTER)
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
