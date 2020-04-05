package com.jzwbxx.dao.impl;

import com.jzwbxx.dao.UserDao;
import com.jzwbxx.model.User;
import org.springframework.stereotype.Repository;

/**
 * Dao - 用户
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {
}