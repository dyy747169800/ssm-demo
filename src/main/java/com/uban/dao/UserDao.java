package com.uban.dao;

import com.uban.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by DuanYangYu on 2016/9/22 0022.
 */
@Repository
public interface UserDao {

    public List<User> selectAll();

    public User selectByPrimaryKey(@Param("id") Integer id);
}
