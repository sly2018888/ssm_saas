package com.sly.web.company;


import com.sly.domain.system.Module;
import com.sly.domain.system.User;
import com.sly.service.system.ModuleService;
import com.sly.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

	@RequestMapping("/login")
	public String login(String email,String password) {
	    if(StringUtils.isEmpty(email)||StringUtils.isEmpty(password)){
            request.setAttribute("error","邮箱或者密码为空");
            return "forward:/login.jsp";
        }
//        使用shiro框架认证  创建令牌 获取主题 开始认证
         password = new Md5Hash(password,email,2).toString();
       //创建令牌UserNamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        //获取主题SecurityUtils.getSubject()
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            request.setAttribute("error","邮箱或者密码错误!");
            return "forward:login.jsp";
        }
        User user = (User) subject.getPrincipal();


//        User user = userService.findByEmail(email);
//        if(user == null){
//            request.setAttribute("error","邮箱错误!");
//            return "forward:/login.jsp";
//        }else {
//            String password_db = user.getPassword();
//            if(!password_page.equals(password_db)){
//                request.setAttribute("error","密码错误!");
//                return "forward:/login.jsp";
//            }
//        }


        session.setAttribute("loginUser",user);
        List<Module> moduleList = moduleService.findMOduleListByUser(user);
        session.setAttribute("moduleList",moduleList);
		return "home/main";
	}

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        SecurityUtils.getSubject().logout();   //登出
//        session.removeAttribute("loginUser");
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home(){
	    return "home/home";
    }
}
