package com.mvpt.services;

import com.mvpt.Constant;
import com.mvpt.dao.IUserDAO;
import com.mvpt.dao.UserDAO;
import com.mvpt.dto.UserDTO;
import com.mvpt.dto.UserMapper;
import com.mvpt.exceptions.NotFoundException;
import com.mvpt.model.Role;
import com.mvpt.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Single Responsibility Principle (SOLID)
public class UserService implements IUserService {
    private IUserDAO userDao;
    private UserMapper userMapper;

    //Singleton Design Pattern
    private static UserService instance;

    private UserService() {
        userMapper = UserMapper.getInstance();
        userDao = UserDAO.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }


    @Override
    public List<UserDTO> findAll() {
        //return userDao.selectAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
        List<UserDTO> users = new ArrayList<>();
        for (User user : userDao.selectAll()) {
            users.add(userMapper.toDTO(user));
        }
        return users;
    }

    @Override
    public UserDTO adminLogin(String username, String password) {
        User user = userDao.login(username, password, Role.ADMIN);
        if (user == null)
            throw new NotFoundException(Constant.USER_NOT_FOUND);
        return userMapper.toDTO(user);
    }

//    public void logout() {
//        currentUser = null;
//    }

    @Override
    public void add(UserDTO newUser) {
        newUser.setCreatedAt(new Date());
        userDao.insert(newUser);
    }

    @Override
    public void update(UserDTO newUser) {
        newUser.setUpdatedAt(new Date());
        userDao.update(newUser);
    }

    @Override
    public boolean existById(Long id) {
        return userDao.existById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public boolean existsByMobile(String phone) {
        return userDao.existsByPhone(phone);
    }

    @Override
    public String getOriginalEmail(Long id) {
        return userDao.getOriginalEmail(id);
    }

    @Override
    public String getOriginalMobile(Long id) {
        return userDao.getOriginalMobile(id);
    }

    @Override
    public User findById(Long id) {
        User user = userDao.selectById(id);
        if (user == null)
            throw new NotFoundException(Constant.USER_NOT_FOUND);

        return user;
    }

}
