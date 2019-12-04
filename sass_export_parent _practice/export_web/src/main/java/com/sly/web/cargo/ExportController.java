package com.sly.web.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sly.domain.cargo.*;
import com.sly.domain.system.User;
import com.sly.domain.vo.ExportProductVo;
import com.sly.domain.vo.ExportResult;
import com.sly.domain.vo.ExportVo;
import com.sly.service.cargo.ContractService;
import com.sly.service.cargo.ExportProductService;
import com.sly.service.cargo.ExportService;
import com.sly.utils.BeanMapUtils;
import com.sly.utils.DownloadUtil;
import com.sly.web.company.BaseController;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/cargo/export")
public class ExportController extends BaseController{

    @Reference
    private ContractService contractService;

    @Reference
    private ExportService exportService;

    @Reference
    private ExportProductService exportProductService;

    @RequestMapping(value = "/contractList",name = "展示已提交的购销合同")
    public String findAll(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        User user = getUser();
        Integer degree = user.getDegree();
        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getCompanyId()).andStateEqualTo(1);
        example.setOrderByClause("create_time desc");
        //细颗粒度控制   权限管理
        if(degree == 4){
            criteria.andCreateByEqualTo(user.getId());
        }else if(degree ==3){
            criteria.andCreateDeptEqualTo(user.getDeptId());
        }else if(degree == 2){
            criteria.andCreateDeptLike(user.getDeptId() + "%");
        }
        PageInfo all = contractService.findAll(example, page, pageSize);
        request.setAttribute("page",all);
        return  "/cargo/export/export-contractList";
    }

    @RequestMapping(value = "/list",name = "查看报运单列表数据")
    public  String list(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        ExportExample example = new ExportExample();
        ExportExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getCompanyId());
        example.setOrderByClause("create_time desc");
        PageInfo all = exportService.findAll(example, page, pageSize);
        request.setAttribute("page",all);
        return "/cargo/export/export-list";
    }

    @RequestMapping(value = "/toExport",name = "报运")
    public String toExport(String id){
        request.setAttribute("id",id);
        return "cargo/export/export-toExport";
    }

    @RequestMapping(value = "/edit",name = "保存报运单")
    public String edit(Export export){
        if(StringUtils.isEmpty(export.getId())){
            export.setId(UUID.randomUUID().toString());
            export.setCompanyId(getCompanyId());
            export.setCompanyName(getCompanyName());
            export.setCreateBy(getUser().getId());
            export.setCreateTime(new Date());
            export.setState(0);
            export.setCreateDept(getUser().getDeptId());
            exportService.save(export);
        }else {
            export.setUpdateTime(new Date());
            export.setUpdateBy(getUser().getId());
            exportService.update(export);
        }
        return "redirect:/cargo/export/list.do";
    }


    @RequestMapping(value = "/toView",name = "查看")
    public String toView(String id){
        Export export = exportService.findById(id);
        request.setAttribute("export",export);
        return "cargo/export/export-view";
    }


    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Export export = exportService.findById(id);
        request.setAttribute("export",export);
        List<ExportProduct> exportProductList  = exportProductService.findByExportId(id);
        request.setAttribute("eps",exportProductList);
        return "/cargo/export/export-update";
    }


    @RequestMapping("/exportE")
    public String exportE(String id){
        Export export = exportService.findById(id);//当前报运单
        ExportVo exportVo = new ExportVo();
        BeanUtils.copyProperties(export,exportVo);
        exportVo.setExportId(id);

        ArrayList<ExportProductVo> exportProductVoList = new ArrayList<>();
        ExportProductVo exportProductVo = null;
        List<ExportProduct> exportProductList = exportProductService.findByExportId(id);//当前报运单下的货物
        for (ExportProduct exportProduct : exportProductList) {
            exportProductVo = new ExportProductVo();
            BeanUtils.copyProperties(exportProduct,exportProductVo);
            exportProductVo.setEid(exportVo.getId());
            exportProductVo.setExportProductId(exportProduct.getId());
            exportProductVoList.add(exportProductVo);
        }
        exportVo.setProducts(exportProductVoList);//将报运单下货物的数据传给vo
        WebClient.create("http://localhost:9090/ws/export/ep").post(exportVo);

        ExportResult exportResult = WebClient.create("http://localhost:9090/ws/export/ep/" + id).get(ExportResult.class);//获取海关修改后的报运单数据
        //ExportResult exportResult = WebClient.create("http://localhost:7878/ws/export/ep/" + id).getResult(id);//获取海关修改后的报运单数据
        exportService.updateE(exportResult);//海关返回数据    当前报运单修改
        return "redirect:/cargo/export/list.do";
    }

    @Autowired
    private DownloadUtil downloadUtil;


    @RequestMapping(value = "/exportPdf",name = "下载报运单pdf")
    public void exportPdf(String id) throws IOException, JRException {
        Export export = exportService.findById(id);
//        获取模板路径
        String realPath = session.getServletContext().getRealPath("/template/export.jasper");
//        读取文件
        FileInputStream fileInputStream = new FileInputStream(realPath);
//        准备数据
        Map<String, Object> parameters = BeanMapUtils.beanToMap(export);
        //报运单货物数据
        List<ExportProduct> exportProductList = exportProductService.findByExportId(id);
//        准备一个数据源
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(exportProductList);
//        填充模板
        JasperPrint jasperPrint = JasperFillManager.fillReport(fileInputStream, parameters, dataSource);
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        把pdf字节数组写入缓存流
        byteArrayOutputStream.write(bytes);
        downloadUtil.download(byteArrayOutputStream,response,"报运单.pdf");
    }


}
