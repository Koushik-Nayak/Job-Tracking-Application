package com.jsp.job.tracking.service;

import org.springframework.stereotype.Service;

import com.jsp.job.tracking.dao.UserDao;
import com.jsp.job.tracking.entity.User;
import com.jsp.job.tracking.entity.UserRole;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    public User register(User user) {
        user.setRole(UserRole.JOB_SEEKER);
        return userDao.save(user);
    }

    public User login(String email, String password) {
        User user = userDao.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
