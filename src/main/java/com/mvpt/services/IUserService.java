package com.mvpt.services;

import com.mvpt.dto.UserDTO;
import com.mvpt.model.User;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAll();

    UserDTO adminLogin(String username, String password);

    void add(UserDTO newUser);

    void update(UserDTO newUser);

    boolean existById(Long id);

    boolean existsByEmail(String email);

    boolean existsByMobile(String phone);

    String getOriginalEmail(Long id);

    String getOriginalMobile(Long id);

    User findById(Long id);


}
