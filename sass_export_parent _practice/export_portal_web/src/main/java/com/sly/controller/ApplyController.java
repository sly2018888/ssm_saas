package com.sly.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sly.domain.company.Company;
import com.sly.service.company.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class ApplyController {

    @Reference
    private CompanyService companyService;

    @RequestMapping("/apply")
    @ResponseBody
    public String apply(Company company){
        try {
            company.setId(UUID.randomUUID().toString());
            company.setState(0);
            companyService.addCompany(company);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

    }
}
