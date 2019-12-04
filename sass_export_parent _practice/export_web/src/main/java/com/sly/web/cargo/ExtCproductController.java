package com.sly.web.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sly.domain.cargo.*;
import com.sly.service.cargo.ContractProductService;
import com.sly.service.cargo.ExtCproductService;
import com.sly.service.cargo.FactoryService;
import com.sly.utils.FileUploadUtil;
import com.sly.web.company.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/extCproduct")
public class ExtCproductController extends BaseController{

    @Reference
    private ExtCproductService extCproductService;
    @Reference
    private ContractProductService contractProductService;
    @Reference
    private FactoryService factoryService;
    @Autowired
    private FileUploadUtil fileUploadUtil;



    @RequestMapping(value = "/list",name = "进入添加货物下附件的页面")
    public String findPage(String contractId,String contractProductId, @RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer pageSize){
//       mapper.xml拼接的条件数据
        FactoryExample factoryexample = new FactoryExample();
        factoryexample.createCriteria().andCtypeEqualTo("附件");
        List<Factory> all = factoryService.findAll(factoryexample);
        request.setAttribute("factoryList",all);

        ExtCproductExample extCproductProductexample = new ExtCproductExample();
        extCproductProductexample.createCriteria().andContractIdEqualTo(contractId);
        PageInfo pageInfo = extCproductService.findAll(extCproductProductexample, page, pageSize);
        request.setAttribute("page",pageInfo);
        request.setAttribute("contractId",contractId);
        request.setAttribute("contractProductId",contractProductId);

        return "cargo/extc/extc-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/cargo/extc/extc-add";
    }

    @RequestMapping(value = "/edit",name = "保存货物下附件的方法")
    public String edit(ExtCproduct extCproduct, MultipartFile productPhoto){
        try {
            String upload = fileUploadUtil.upload(productPhoto);
            extCproduct.setProductImage(upload);//七牛云返回的url
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(StringUtils.isEmpty(extCproduct.getId())){
            extCproduct.setId(UUID.randomUUID().toString());
            extCproduct.setCreateTime(new Date());
            extCproductService.save(extCproduct);
        }else {
            extCproduct.setUpdateTime(new Date());
            extCproductService.update(extCproduct);
        }
        return "redirect:/cargo/extCproduct/list.do?contractId="+extCproduct.getContractId()+"&contractProductId="+extCproduct.getContractProductId();
    }

    @RequestMapping(value = "toUpdate",name = "进入修改货物下附件页面")
    public String toUpdate(String id,String contractId ,String contractProductId){
        ExtCproduct extCproduct = extCproductService.findById(id);
        request.setAttribute("extCproduct",extCproduct);
//        生产货物的工厂
        FactoryExample factoryexample = new FactoryExample();
        factoryexample.createCriteria().andCtypeEqualTo("附件");
        List<Factory> all = factoryService.findAll(factoryexample);
        request.setAttribute("factoryList",all);

        request.setAttribute("contractId",contractId);
        request.setAttribute("contractProductId",contractProductId);
        return "/cargo/extc/extc-update";
    }

    @RequestMapping("/delete")
    public String delete(String id,String contractId,String contractProductId){
        extCproductService.delete(id);
        return "redirect:/cargo/extCproduct/list.do?contractId=" + contractId + "&contractProductId="+contractProductId;
    }

}
