package cn.itcast.controller;


import cn.itcast.domain.system.Module;
import cn.itcast.domain.system.User;
import cn.itcast.service.system.ModuleService;
import cn.itcast.service.system.UserService;
import cn.itcast.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController{

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @Autowired
    private ModuleService moduleService;

	@RequestMapping("/login")
	public String login(String email, String password) {
//        0、email和password不能为空
        if(StringUtils.isEmpty(email)||StringUtils.isEmpty(password)){
            request.setAttribute("error","邮箱或密码不能为空");
//            重定向：相当于让浏览器重新发起了一次请求，之前的request和response丢失了
            return "forward:/login.jsp";  //请求转发
        }
//        使用shiro框架的认证（登录）方式 有三步 1、创建令牌 （用户输入的信息用户名和密文的密码） 2、获取主题  3、开始认证
        password = new Md5Hash(password,email,2).toString();
//        1、创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(email,password);
//        2、获取主题
        Subject subject = SecurityUtils.getSubject();
//        3、开始认证
        try {
            subject.login(token); //AuthenticationToken
        } catch (AuthenticationException e) {
            request.setAttribute("error","邮箱或密码错误");
            return "forward:/login.jsp";  //请求转发
        }
//        页面上需要从session中获取当前登录人和当前登录人的菜单

//        获取主角：user
        User user = (User) subject.getPrincipal();

        session.setAttribute("loginUser",user);
        //        根据用户查询菜单
        List<Module> moduleList = moduleService.findModuleListByUser(user);
        session.setAttribute("modules",moduleList);
        return "home/main";
/*
//	    1、根据email查询用户
       User user = userService.findByEmail(email);
//        2、判断是否能根据email查询到用户数据
        if(user==null){
            request.setAttribute("error","邮箱错误");
            return "forward:/login.jsp";  //请求转发
        }else{
            //        3、如果能查询到 ，把页面上传过来的password加密在跟user中的password比较
            String password_db = user.getPassword(); //密文
//            把页面上的密码加密
            String password_page = new Md5Hash(password,email,2).toString();
            if(!password_db.equals(password_page)){
                request.setAttribute("error","密码错误");
                return "forward:/login.jsp";  //请求转发
            }
        }
//        email和password都正确
//        把当前登录人的信息存到session中
        session.setAttribute("loginUser",user);

//        根据用户查询菜单
       List<Module> moduleList = moduleService.findModuleListByUser(user);
       session.setAttribute("modules",moduleList);
*/


	}

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        SecurityUtils.getSubject().logout();   //登出
//        session.removeAttribute("loginUser");  //不用
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home(){
	    return "home/home";
    }

    @RequestMapping(value = "/weixinlogin",name = "微信登录")
    public String weixinlogin(String code, String state, Model model){
        User loginUser = null;
        System.out.println("微信登录...");
        if (code==null||code.equals("")){
            model.addAttribute("error","授权失败...");
            return "forward:/login.jsp";
        }else {
            System.out.println("授权成功");
            //通过code换取access_token
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3bdb1192c22883f3&secret=db9d6b88821df403e5ff11742e799105&code="+code+"&grant_type=authorization_code";
            Map<String, Object> objectMap = HttpUtil.sendGet(url);
            //获取真实令牌
            Object access_token = objectMap.get("access_token");
            //获取授权用户唯一标识
            String openid = objectMap.get("openid").toString();
            //根据token和openid可以获取用户信息
            String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid;
            Map<String, Object> userInfoMap = HttpUtil.sendGet(getUserInfo);
            //昵称和头像
            String nickname = userInfoMap.get("nickname").toString();
            String headimgurl = userInfoMap.get("headimgurl").toString();
            if(access_token != null && openid != null) {

                loginUser = userService.findByVx(openid);
                //添加到session中loginuser
                if (loginUser==null){
                    //没有查询到用户,进行注册操作
                    loginUser=new User();
                    loginUser.setVxOpenid(openid);
                    loginUser.setUserName(nickname);
                    loginUser.setCreateTime(new Date());
                    loginUser.setId(UUID.randomUUID().toString());
                    loginUser.setCompanyId("1");
                    loginUser.setDeptId("100");
                    loginUser.setPassword("123456");
                    userService.save(loginUser);
                    //新注册的用户先分配一个老王的权限
                    String[] roleIds = {"4028a1cd4ee2d9d6014ee2df4c6a0001"};//角色id数组
                    userService.changeRole(loginUser.getId(),roleIds);
                }
                //最终进行shiro登录授权
                Subject subject = SecurityUtils.getSubject();
                try {
                    UsernamePasswordToken token = new UsernamePasswordToken(openid, "");
                    subject.login(token);
                }catch (Exception e){
                    model.addAttribute("error","微信授权登录错误...");
                    return "forward:/login.jsp";
                }
                //获取主角：user此时是已经获取登陆令牌的用户
                loginUser= (User) subject.getPrincipal();

                session.setAttribute("loginUser",loginUser);
                session.setAttribute("headimgurl",headimgurl);
                return "redirect:main.do";


            }else {
                model.addAttribute("error","令牌或者用户唯一标识为空...");
                return "forward:/login.jsp";
            }
        }
    }

    @RequestMapping(value = "/main",name = "主菜单")
    public String main(){
        User loginUser = (User) session.getAttribute("loginUser");
        List<Module> moduleList = moduleService.findModuleListByUser(loginUser);
        session.setAttribute("modules",moduleList);

        //查出当前用户的可查看模块,展示到页面
        return "home/main";
    }
}
