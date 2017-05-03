package com.uban.service;

import com.uban.entity.User;

import java.util.List;

/**
 * Created by DuanYangYu on 2016/9/1 0001.
 */
public interface UserService {

    List<User> selectUsers();

    User selectUserById(Integer id);
}
