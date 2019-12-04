package com.sly.web.system;

import com.github.pagehelper.PageInfo;
import com.sly.domain.system.Module;
import com.sly.service.system.ModuleService;
import com.sly.web.company.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/module")
public class ModuleController extends BaseController{

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list",name = "展示企业列表数据")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo pb = moduleService.findByPage(page,pageSize);
        request.setAttribute("page",pb);
        return "/system/module/module-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        List<Module> moduleList = moduleService.findAll();
        request.setAttribute("menus",moduleList);
        return "/system/module/module-add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Module module = moduleService.findById(id);
        request.setAttribute("module",module);
        List<Module> moduleList = moduleService.findAll();
        request.setAttribute("menus",moduleList);
        return "/system/module/module-update";
    }

    @RequestMapping("/edit")
    public String edit(Module module){
        if("".equals(module.getParentId())){
            module.setParentId(null);
        }
        String id = module.getId();
        if("".equals(id)){
            module.setId(UUID.randomUUID().toString());
            moduleService.addModule(module);
        }else {
            moduleService.updateModule(module);
        }

        return "redirect:/system/module/list.do";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        moduleService.deleteById(id);
        return "redirect:/system/module/list.do";
    }



}
