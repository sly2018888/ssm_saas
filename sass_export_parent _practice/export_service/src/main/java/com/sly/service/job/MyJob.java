package com.sly.service.job;

import com.sly.dao.system.UserDao;
import com.sly.domain.system.User;
import com.sly.utils.MailUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyJob {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void run(){
        Date date = new Date();
        SimpleDateFormat sdf  = new SimpleDateFormat("MM-dd");
        String format = sdf.format(date);
        List<User> userList = userDao.findByBirthday(format);
//        List<User> userList = userDao.findAll();
        Map<String,String> map = null;
        for (User user : userList) {
            String birthday = user.getBirthday();
            String birthday1 = birthday.substring(5, birthday.length());
            map = new HashMap<>();
            if(format.equals(birthday1)){
                map.put("to",user.getEmail());
                map.put("subject","生日快乐");
                map.put("content",user.getUserName() + ",sass-export货代云平台祝您生日快乐!!");
                amqpTemplate.convertAndSend("mail.birthday",map);
            }
        }
    }
}
