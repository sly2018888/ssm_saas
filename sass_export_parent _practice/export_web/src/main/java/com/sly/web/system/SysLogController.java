package com.sly.web.system;

import com.github.pagehelper.PageInfo;
import com.sly.service.system.SysLogService;
import com.sly.web.company.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/system/log")
public class SysLogController extends BaseController{

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/list")
    public String findAll(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        PageInfo pageInfo = sysLogService.findAll(page,pageSize);
        request.setAttribute("page",pageInfo);
        return "/system/log/log-list";
    }

}
