package com.sly.service.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.User;

public interface UserService {
    PageInfo<User> findAll(Integer page, Integer pageSize);

    User findById(String userId);

    void addUser(User user);

    void updateUser(User user);

    void deleteById(String userId);

    void changeRole(String userid, String[] roleIds);

    User findByEmail(String email);
}
