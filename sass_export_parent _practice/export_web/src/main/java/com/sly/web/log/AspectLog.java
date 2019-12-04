package com.sly.web.log;

import com.sly.domain.system.SysLog;
import com.sly.domain.system.User;
import com.sly.service.system.SysLogService;
import org.apache.shiro.session.Session;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class AspectLog {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @Around("execution(* com.sly.web.*.*.*(..))")
    public Object saveLog(ProceedingJoinPoint pjp) throws Throwable {
        SysLog sysLog = new SysLog();
        User user = (User) session.getAttribute("loginUser");
        if(user!=null){
            sysLog.setCompanyId(user.getCompanyId());
            sysLog.setCompanyName(user.getCompanyName());
            sysLog.setUserName(user.getUserName());
        }
        sysLog.setIp(request.getRemoteAddr());
        sysLog.setId(UUID.randomUUID().toString());

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        sysLog.setMethod(method.getName());

        if(method.isAnnotationPresent(RequestMapping.class)){
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            String name = annotation.name();
            sysLog.setAction(name);
        }
        sysLog.setTime(new Date());

        sysLogService.save(sysLog);
        return pjp.proceed();//运行原方法

    }

}
