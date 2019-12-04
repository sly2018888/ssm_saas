package com.sly.email;

import com.alibaba.fastjson.JSON;
import com.sly.utils.MailUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.*;

public class BirthdayConsumer implements MessageListener{


    @Override
    public void onMessage(Message message) {
        try {
        byte[] body = message.getBody();
        Map<String,String> map = JSON.parseObject(body,Map.class);
        String to = map.get("to");
        String subject = map.get("subject");
        String content = map.get("content");

            MailUtil.sendMsg(to,subject,content);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
