package com.sly.web.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sly.domain.cargo.*;
import com.sly.service.cargo.ContractProductService;
import com.sly.service.cargo.FactoryService;
import com.sly.utils.FileUploadUtil;
import com.sly.web.company.BaseController;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/contractProduct")
public class ContractProductController extends BaseController{

    @Reference
    private ContractProductService contractProductService;
    @Reference
    private FactoryService factoryService;
    @Autowired
    private FileUploadUtil fileUploadUtil;


    @RequestMapping(value = "/list",name = "进入添加合同下货物的页面")
    public String findPage(String contractId, @RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer pageSize){
        request.setAttribute("contractId",contractId);

//       mapper.xml拼接的条件数据
        FactoryExample factoryexample = new FactoryExample();
        factoryexample.createCriteria().andCtypeEqualTo("货物");
        List<Factory> all = factoryService.findAll(factoryexample);
        request.setAttribute("factoryList",all);

        ContractProductExample contractProductexample = new ContractProductExample();
        contractProductexample.createCriteria().andContractIdEqualTo(contractId);
        PageInfo pageInfo = contractProductService.findAll(contractProductexample, page, pageSize);
        request.setAttribute("page",pageInfo);
        return "/cargo/product/product-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/cargo/product/product-add";
    }

    @RequestMapping(value = "/edit",name = "保存合同下货物的方法")
    public String edit(ContractProduct contractProduct, MultipartFile productPhoto){
        if(StringUtils.isEmpty(contractProduct.getId())){
            contractProduct.setId(UUID.randomUUID().toString());
            contractProduct.setCreateTime(new Date());
            String upload = null;
            try {
                upload = fileUploadUtil.upload(productPhoto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            contractProduct.setProductImage(upload);//七牛云返回的url
            contractProductService.save(contractProduct);
        }else {
            contractProduct.setUpdateTime(new Date());
            contractProductService.update(contractProduct);
        }
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractProduct.getContractId();
    }

    @RequestMapping(value = "toUpdate",name = "进入修改合同下货物页面")
    public String toUpdate(String id){
        ContractProduct contractProduct = contractProductService.findById(id);
        request.setAttribute("contractProduct",contractProduct);
//        生产货物的工厂
        FactoryExample factoryexample = new FactoryExample();
        factoryexample.createCriteria().andCtypeEqualTo("货物");
        List<Factory> all = factoryService.findAll(factoryexample);
        request.setAttribute("factoryList",all);
        return "/cargo/product/product-update";
    }

    @RequestMapping("/delete")
    public String delete(String id,String contractId){
        contractProductService.delete(id);
        return "redirect:/cargo/contractProduct/list.do?contractId=" + contractId;
    }

    @RequestMapping("/toImport")
    public String toImport(String contractId){
        request.setAttribute("contractId",contractId);
        return "/cargo/product/product-import";
    }

    @RequestMapping(value = "/import",name = "合同上传货物的方法")
    public String importXls(String contractId,MultipartFile file) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        XSSFRow row = null;
        List<ContractProduct> productList = new ArrayList<>();
        for (int i = 1; i <= lastRowNum; i++) {
            ContractProduct contractProduct = new ContractProduct();
            row =  sheet.getRow(i);
            String factoryValue = row.getCell(1).getStringCellValue();
            contractProduct.setFactoryName(factoryValue);//生产厂家
            String productNo = row.getCell(2).getStringCellValue();
            contractProduct.setProductNo(productNo);//货号
            Double cnumber = row.getCell(3).getNumericCellValue();
            contractProduct.setCnumber(cnumber.intValue());//数值型   数量
            String packageUnit = row.getCell(4).getStringCellValue();
            contractProduct.setPackingUnit(packageUnit);//包装单位
            Double loadingRate = row.getCell(5).getNumericCellValue();
            contractProduct.setLoadingRate(loadingRate.toString());//装率
            Double boxNum = row.getCell(6).getNumericCellValue();
            contractProduct.setBoxNum(boxNum.intValue());//箱数
            Double price = row.getCell(7).getNumericCellValue();
            contractProduct.setPrice(price);//单价
            String productDesc = row.getCell(8).getStringCellValue();
            contractProduct.setProductDesc(productDesc);//获取描述
            String productRequest = row.getCell(9).getStringCellValue();
            contractProduct.setProductRequest(productRequest);//要求
            contractProduct.setId(UUID.randomUUID().toString());//id
            contractProduct.setContractId(contractId);   //货物id
            contractProduct.setCreateTime(new Date());
            contractProduct.setCompanyId(getCompanyId());
            contractProduct.setCompanyName(getCompanyName());
            contractProduct.setCreateDept(getUser().getDeptId());
            contractProduct.setCreateBy(getUser().getId());
            productList.add(contractProduct);
        }
        contractProductService.saveList(productList);
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }


}
