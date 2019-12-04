package com.sly.web.cargo;

import com.sly.domain.cargo.ContractProduct;
import com.sly.service.cargo.ContractProductService;
import com.sly.web.company.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/stat")
public class StatController extends BaseController{

    @Reference
    private ContractProductService contractProductService;

    @RequestMapping("/toCharts")
    public String toCharts(String chartsType){
        return "/stat/stat-"+chartsType;
    }

    @ResponseBody
    @RequestMapping("/sellCharts")
    public List<Map> sellCharts(){
        List<Map> sellList = contractProductService.sellCharts(getCompanyId());
        return  sellList;
    }

    @ResponseBody
    @RequestMapping("/factoryCharts")
    public List<Map> factoryCharts(){
        List<Map> factoryList = contractProductService.factoryCharts(getCompanyId());
        return  factoryList;
    }

    @ResponseBody
    @RequestMapping("/onlineCharts")
    public List<Map> onlineCharts(){
        List<Map> onlineList = contractProductService.onlineCharts(getCompanyId());
        return  onlineList;
    }

}
