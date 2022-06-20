package com.mvpt.dao;

import com.mvpt.dto.UserDTO;
import com.mvpt.model.Role;
import com.mvpt.model.User;

import java.util.List;

public interface IUserDAO {
    List<User> selectAll();

    User login(String username, String password, Role role);

    void insert(UserDTO newUser);

    void update(UserDTO newUser);

    User selectById(Long id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existById(Long id);

    String getOriginalEmail(Long id);

    String getOriginalMobile(Long id);
}
