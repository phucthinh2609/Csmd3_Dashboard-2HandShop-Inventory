package com.mvpt.dao;

import com.mvpt.dto.UserDTO;
import com.mvpt.model.Role;
import com.mvpt.model.User;

import java.util.List;

public interface IUserDAO {
    List<User> selectAll();

    User login(String username, String password);

    void insert(UserDTO newUser);

    void update(UserDTO newUser);

    User selectById(Integer id);

    User selectByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPassword(String password);

    boolean existsByPhone(String phone);

    boolean existById(Integer id);

    String getOriginalEmail(Integer id);

    String getOriginalMobile(Integer id);

    List<User> searchUser(String keySearch);

    List<User> selectUsersById(Integer id);
}
