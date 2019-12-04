package com.sly.web.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.Dept;
import com.sly.domain.system.Role;
import com.sly.domain.system.User;
import com.sly.service.system.DeptService;
import com.sly.service.system.RoleService;
import com.sly.service.system.UserService;
import com.sly.utils.MailUtil;
import com.sly.web.company.BaseController;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String findAll(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        PageInfo<User> pageInfo = userService.findAll(page,pageSize);
        request.setAttribute("page",pageInfo);
        return  "/system/user/user-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        List<Dept> deptList = deptService.findAllDept(getCompanyId());
        request.setAttribute("deptList",deptList);
        return "/system/user/user-add";
    }

    @RequestMapping("/edit")
    public String edit(User user) throws Exception {
        if("".equals(user.getDeptId())){
            user.setDeptId(null);
        }
        if("".equals(user.getId())){
        //if(StringUtils.isEmpty(user.getId())){
            user.setId(UUID.randomUUID().toString());
            user.setCompanyId(getCompanyId());
            user.setCompanyName(getCompanyName());
            user.setCreateTime(new Date());
            //String password = user.getPassword();
            //String md5 = new Md5Hash(password,user.getEmail(),2).toString();
            //user.setPassword(md5);
            userService.addUser(user);
            //MailUtil.sendMsg(user.getEmail(),"恭喜注册成功！","欢迎使用saas-export平台,你的密码是:" + password);
        }else {
            user.setUpdateTime(new Date());
            userService.updateUser(user);
        }
        return "redirect:/system/user/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        List<Dept> deptList = deptService.findAllDept(getCompanyId());
        User user = userService.findById(id);
        request.setAttribute("deptList",deptList);
        request.setAttribute("user",user);
        return "/system/user/user-update";
    }

    @RequestMapping("/delete")
    public String deleteById(String id){
        userService.deleteById(id);
        return "redirect:/system/user/list.do";
    }


    @RequestMapping("/roleList")
    public String roleList(String id){
        //查询用户信息
        User user = userService.findById(id);
        //查询用户拥有的角色
        List<String> roles = roleService.findRolesById(id);
        //查询公司所有角色
        List<Role> roleList = roleService.findAllRole(getCompanyId());
        StringBuilder sb = new StringBuilder();
        String collect = roles.stream().collect(Collectors.joining(","));
        request.setAttribute("user",user);
        request.setAttribute("userRoleStr",collect);
        request.setAttribute("roleList",roleList);
        return "/system/user/user-role";
    }



    @RequestMapping("/changeRole")
    public String changeRole(String userid,String[] roleIds){
        userService.changeRole(userid,roleIds);
        return "redirect:/system/user/list.do";
    }




}
