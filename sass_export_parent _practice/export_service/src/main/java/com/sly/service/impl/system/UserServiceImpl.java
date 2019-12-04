package com.sly.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.dao.system.UserDao;
import com.sly.domain.system.User;
import com.sly.service.system.UserService;
import org.apache.regexp.RE;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<User> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<User> userList = userDao.findAll();
        return new PageInfo<>(userList);
    }

    @Override
    public User findById(String userId) {
        return userDao.findById(userId);
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void addUser(User user) {
        String password = user.getPassword();
        user.setPassword(new Md5Hash(user.getPassword(),user.getEmail(),2).toString());
        userDao.addUser(user);

        Map<String,String> map = new HashMap<>();
        map.put("to",user.getEmail());
        map.put("subject","入住sass-export平台");
        map.put("content","欢迎加入sass-export平台,您可以使用邮箱和密码登录项目,密码是:"+password);
        amqpTemplate.convertAndSend("mail.send",map);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(new Md5Hash(user.getPassword(), user.getEmail(), 2).toString());
        userDao.updateUser(user);
    }

    @Override
    public void deleteById(String userId) {
        userDao.deleteById(userId);
    }

    @Override
    public void changeRole(String userid, String[] roleIds) {
        userDao.deleteUserRole(userid);
        for (String roleId : roleIds) {
            userDao.addUserRole(userid,roleId);
        }
    }

    @Override
    public User findByEmail(String email) {
        return  userDao.findByEmail(email);
    }


}
