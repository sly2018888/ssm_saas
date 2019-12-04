package com.sly.web.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.Module;
import com.sly.domain.system.Role;
import com.sly.domain.system.User;
import com.sly.service.system.ModuleService;
import com.sly.service.system.RoleService;
import com.sly.service.system.UserService;
import com.sly.web.company.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;



    @RequestMapping(value = "/list",name = "查询角色列表数据")
    public String findAll(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        PageInfo<Role> page1 = roleService.findAll(getCompanyId(),page,pageSize);
        request.setAttribute("page",page1);
        return "/system/role/role-list";
    }


    @RequestMapping(value = "/toAdd",name = "跳转添加角色页面")
    public String toAdd(){
        return "/system/role/role-add";
    }

    @RequestMapping(value = "/edit",name = "角色添加  角色修改")
    public String edit(Role role){
        if("".equals(role.getId())){
            role.setId(UUID.randomUUID().toString());
            role.setCompanyId(getCompanyId());
            role.setCompanyName(getCompanyName());
            role.setCreateTime(new Date());
            roleService.addRole(role);
        }else {
            role.setUpdateTime(new Date());
            roleService.updateRole(role);
        }
        return "redirect:/system/role/list.do";
    }

    @RequestMapping(value = "/toUpdate",name = "跳转修改角色页面")
    public String toUpdate(String id){
        Role role = roleService.findById(id);
        request.setAttribute("role",role);
        return "/system/role/role-update";
    }

    @RequestMapping(value = "/delete",name = "删除角色")
    public String deleteById(String id){
        roleService.deleteById(id);
        return "redirect:/system/role/list.do";
    }


    @RequestMapping(value = "/getZtreeNodes",name = "获取树形节点数据")
    @ResponseBody
    public  List<Map> getZtreeNodes(String roleid){
        List<Module> ztreeNodes = moduleService.findAll();
        List<Map> listMap = new ArrayList<>();
        Map map = null;
        //查询节点的选中状态 并设置
        List<String> moduleIds = roleService.findModulesByRoleId(roleid);
        for (Module ztreeNode : ztreeNodes) {
            map = new HashMap<>();
            String id = ztreeNode.getId();
            map.put("id",ztreeNode.getId());
            map.put("pId",ztreeNode.getParentId());
            map.put("name",ztreeNode.getName());
            if(moduleIds.contains(id)){
                map.put("checked",true);
            }
            listMap.add(map);
        }
        return listMap;
        }


    @RequestMapping(value = "/roleModule",name = "角色信息带参数跳转")
    public String roleModule(String roleid){
        Role role = roleService.findById(roleid);
        request.setAttribute("role",role);
        return "/system/role/role-module";
    }

    @RequestMapping(value = "/updateRoleModule",name = "修改角色权限")
    public String updateRoleModule(String roleid,String moduleIds){
        roleService.addRoleModule(roleid,moduleIds);
        return "redirect:/system/role/list.do";
    }







}
