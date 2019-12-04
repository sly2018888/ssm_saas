package com.sly.web.company;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sly.domain.company.Company;
import com.sly.service.company.CompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Reference
    private CompanyService companyService;

//    @RequestMapping(value = "/findAll",name = "展示企业列表数据")
//    public String findAll(HttpServletRequest request){
//        List<Company> all = companyService.findAll();
//        request.setAttribute("list",all);
//        return "/company/company-list";
//    }


    @RequestMapping(value = "/list",name = "展示企业列表数据")
    @RequiresPermissions("企业管理")
    public String findAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo pb = companyService.findByPage(page,pageSize);
        request.setAttribute("page",pb);
        return "/company/company-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/company/company-add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Company company = companyService.findById(id);
        request.setAttribute("company",company);
        return "/company/company-update";
    }

    @RequestMapping("/edit")
    public String edit(Company company){
        String id = company.getId();
        if("".equals(id)){
            company.setId(UUID.randomUUID().toString());
            companyService.addCompany(company);
        }else {
            companyService.updateCompany(company);
        }

        return "redirect:/company/list.do";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        companyService.delete(id);
        return "redirect:/company/list.do";
    }

}
