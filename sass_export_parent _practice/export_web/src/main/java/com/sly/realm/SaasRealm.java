package com.sly.realm;

import com.sly.domain.system.Module;
import com.sly.domain.system.User;
import com.sly.service.system.ModuleService;
import com.sly.service.system.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaasRealm extends AuthorizingRealm{


    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("------进入了授权方法AuthorizationInfo");
        //告诉shiro框架 当前登录人有哪些菜单权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Module> moduleList = moduleService.findMOduleListByUser(user);
//        根据用户查询所拥有的的菜单权限
        for (Module module : moduleList) {
            authorizationInfo.addStringPermission(module.getName());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("+++++进入了认证方法AuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String email = token.getUsername();
        String password_page = new String (token.getPassword());
        User user = userService.findByEmail(email);
        if(user!=null){
            String password_db = user.getPassword();
            if(password_db.equals(password_page)){
                return new SimpleAuthenticationInfo(user,password_db,getName());
            }
        }
        return null;
    }
}
