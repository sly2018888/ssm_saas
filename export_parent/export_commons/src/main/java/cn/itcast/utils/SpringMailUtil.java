package cn.itcast.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SpringMailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendBirthdayEmail(String to, String subject, String context) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();//创建邮件对象
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");//邮件助手
            helper.setFrom("itcast_server@sina.com");
            helper.setTo(to);
            helper.setSubject(subject);
            //helper.setText(context,"<img src='https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1575624797&di=ad554263f590e44d71863494be5471a9&src=http://hbimg.b0.upaiyun.com/13c2b55d108f0866fe18c6d333d845ab2502a73b491bb-HYXADi_fw658'>");
            helper.setText("<h2>亲爱的Saas系统用户,今天是您的生日，祝您生日快乐！<h2><img src='https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1575624797&di=ad554263f590e44d71863494be5471a9&src=http://hbimg.b0.upaiyun.com/13c2b55d108f0866fe18c6d333d845ab2502a73b491bb-HYXADi_fw658'>",true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}