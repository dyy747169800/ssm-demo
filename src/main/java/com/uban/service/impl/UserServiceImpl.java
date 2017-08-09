package com.uban.service.impl;

import com.uban.dao.UserDao;
import com.uban.entity.User;
import com.uban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by DuanYangYu on 2016/9/1 0001.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectUsers() {

        return userDao.selectAll();
    }

    @Override
    public User selectUserById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public void editUserById(User user) {
        userDao.editUserById(user);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }


}
