package com.sly.web.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.domain.system.Dept;
import com.sly.service.system.DeptService;
import com.sly.web.company.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/list",name = "分页显示所有部门数据")
    public String findAll(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        PageInfo<Dept> pageInfo =  deptService.findAll(page,pageSize,getCompanyId());
        request.setAttribute("page",pageInfo);
        return "/system/dept/dept-list";
    }

    @RequestMapping(value = "/toAdd",name = "点击新建按钮 部门下拉列表显示")
    public String toAdd(){
        List<Dept> deptList = deptService.findByDeptId(getCompanyId());
        request.setAttribute("deptList",deptList);
        return "/system/dept/dept-add";
    }

    @RequestMapping(value = "/edit",name = "没有id新建部门   有id修改部门")
    public String edit(Dept dept){
        if("".equals(dept.getParent().getDeptId())){
            dept.getParent().setDeptId(null);
        }
        if("".equals(dept.getDeptId())){
            dept.setDeptId(UUID.randomUUID().toString());
            dept.setCompanyId(getCompanyId());
            dept.setCompanyName(getCompanyName());
            deptService.addDept(dept);
        }else {
            deptService.updateDept(dept);
        }
        return "redirect:/system/dept/list.do";
    }

    @RequestMapping("/toUpdate")
    public String findById(String id){
           Dept dept =  deptService.findById(id);
           List<Dept> deptList = deptService.findAllDept(getCompanyId());
           request.setAttribute("dept",dept);
           request.setAttribute("deptList",deptList);
           return "/system/dept/dept-update";
    }

    @RequestMapping("/delete")
    public String delete(String deptId){
        deptService.delete(deptId);
        return "redirect:/system/dept/list.do";
    }

}
