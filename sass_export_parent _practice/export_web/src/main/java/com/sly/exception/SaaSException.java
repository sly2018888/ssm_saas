package com.sly.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaaSException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mav = new ModelAndView();
        if(ex instanceof UnauthorizedException){
            mav.setViewName("forward:/unauthorized.jsp");
        }else {
            mav.setViewName("error");
        }
        mav.addObject("errorMsg",ex.getMessage());
        return mav;
    }
}
