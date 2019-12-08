package cn.itcast;

import com.itcast.util.MailUtil;
import com.itcast.util.SpringMailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext_mail.xml")
public class MailTest {

    //@Autowired
    //private MailUtil mailUtil;
    @Autowired
    private SpringMailUtil springMailUtil;

    @Test
    public void testSend(){
//        mailUtil.sendEmail("shiyilong128@126.com","spring整合邮件","看能不能收到信息！！！");
//        mailUtil.sendEmailWithAttachment("shiyilong128@126.com","spring整合邮件","看能不能收到信息！！！");
        //mailUtil.sendEmailWithAttachmentAndContentPic("shiyilong128@126.com","spring整合邮件","看能不能收到信息！！！");
        springMailUtil.sendBirthdayEmail("huixinwei@163.com","11111111111","1111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println("++++++++++++++");
    }


}
