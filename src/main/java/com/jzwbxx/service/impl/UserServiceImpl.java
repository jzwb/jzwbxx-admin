package com.jzwbxx.service.impl;

import com.jzwbxx.common.Filter;
import com.jzwbxx.common.Principal;
import com.jzwbxx.dao.UserDao;
import com.jzwbxx.exception.ServiceException;
import com.jzwbxx.model.User;
import com.jzwbxx.service.UserService;
import com.jzwbxx.util.WebUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service - 用户
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    public void setBaseDao(UserDao userDao) {
        super.setBaseDao(userDao);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUnionId(String unionId) {
        if (StringUtils.isBlank(unionId)) {
            return null;
        }
        List<User> users = userDao.findList(null, null, Collections.singletonList(Filter.eq("unionId", unionId)), null);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public void login(User user, boolean isPasswordLogin, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServiceException {
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        if (isPasswordLogin) {
            if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
                throw new ServiceException("用户名或密码错误");
            }
        } else {
        }
        Map<String, Object> attributes = new HashMap<>();
        Enumeration<?> keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            attributes.put(key, session.getAttribute(key));
        }
        session.invalidate();
        session = request.getSession();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue());
        }
        session.setAttribute(User.PRINCIPAL_ATTRIBUTE_NAME, new Principal(user.getId(), user.getEmail()));
        WebUtils.addLoginCookies(user, request, response);
    }

    @Override
    @Transactional(readOnly = true)
    public User genRegisterUser(String email, String mobile, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPassword(DigestUtils.md5Hex(StringUtils.isBlank(password) ? "123456" : password));
        return user;
    }

    @Override
    public void register(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServiceException {
        if (user == null) {
            throw new ServiceException("注册失败，用户不能为null");
        }
        if (user.getId() == null) {
            userDao.persist(user);
        } else {
            userDao.merge(user);
        }
        Map<String, Object> attributes = new HashMap<>();
        Enumeration<?> keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            attributes.put(key, session.getAttribute(key));
        }
        session.invalidate();
        session = request.getSession();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue());
        }
        session.setAttribute(User.PRINCIPAL_ATTRIBUTE_NAME, new Principal(user.getId(), user.getEmail()));
        WebUtils.addLoginCookies(user, request, response);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) throws ServiceException {
        if (StringUtils.isBlank(email)) {
            throw new ServiceException("参数错误");
        }
        List<User> users = userDao.findList(null, null, Collections.singletonList(Filter.eq("email", email)), null);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        if (users.size() > 1) {
            throw new ServiceException("账号异常");
        }
        return users.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByMobile(String mobile) throws ServiceException {
        if (StringUtils.isBlank(mobile)) {
            throw new ServiceException("参数错误");
        }
        List<User> users = userDao.findList(null, null, Collections.singletonList(Filter.eq("mobile", mobile)), null);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        if (users.size() > 1) {
            throw new ServiceException("账号异常");
        }
        return users.get(0);
    }
}