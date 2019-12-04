package com.sly.dao.system;

import com.sly.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findById(String userId);

    void addUser(User user);

    void updateUser(User user);

    void deleteById(String userId);

    void deleteUserRole(String userid);

    void addUserRole(@Param("userid") String userid,@Param("roleId") String roleId);

    User findByEmail(String email);

    List<User> findByBirthday(String birthday);
}
