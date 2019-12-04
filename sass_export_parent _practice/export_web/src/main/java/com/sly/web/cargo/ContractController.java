package com.sly.web.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sly.domain.cargo.Contract;
import com.sly.domain.cargo.ContractExample;
import com.sly.domain.system.User;
import com.sly.domain.vo.ContractProductVo;
import com.sly.service.cargo.ContractService;
import com.sly.utils.DownloadUtil;
import com.sly.web.company.BaseController;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController{

    @Reference
    private ContractService contractService;

    @RequestMapping("/list")
    public String findPage(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        User user = getUser();
        Integer degree = user.getDegree();

        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getCompanyId());

        if(degree == 4){
            criteria.andCreateByEqualTo(user.getId());
        }else if(degree ==3){
            criteria.andCreateDeptEqualTo(user.getDeptId());
        }else if(degree == 2){
            criteria.andCreateDeptLike(user.getDeptId() + "%");
        }

        //设置排序
        example.setOrderByClause("create_time desc");
        PageInfo all = contractService.findAll(example, page, pageSize);
        request.setAttribute("page",all);
        return "/cargo/contract/contract-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/cargo/contract/contract-add";
    }

    @RequestMapping("/edit")
    public String edit(Contract contract){
        if(StringUtils.isEmpty(contract.getId())){
            contract.setId(UUID.randomUUID().toString());
            contract.setCreateTime(new Date());
            contract.setState(0);
            contract.setCompanyId(getCompanyId());
            contract.setCompanyName(getCompanyName());
            contract.setCreateBy(getUser().getId());//当前登录人的id
            contract.setCreateDept(getUser().getDeptId());//当前登录人的部门id
            contractService.save(contract);
        }else {
            contract.setUpdateTime(new Date());
            contractService.update(contract);
        }
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(String id){
        Contract contract = contractService.findById(id);
        request.setAttribute("contract",contract);
        return "/cargo/contract/contract-update";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        contractService.delete(id);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("/toView")
    public String toView(String id){
        Contract contract = contractService.findById(id);
        request.setAttribute("contract",contract);
        return "/cargo/contract/contract-view";
    }

    @RequestMapping("/submit")
    public String submit(String id){
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(1);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("/cancel")
    public String cancel(String id){
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(0);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("/print")
    public String print(){
        return "cargo/print/contract-print";
    }


    @Autowired
    private DownloadUtil downloadUtil;

    @RequestMapping(value = "/printExcelWithTemplate",name = "模板导出")
    public void printExcelWithTemplate(String inputDate) throws IOException {

        List<ContractProductVo> contractProductVoList =  contractService.findContractProductVoByShipTime(inputDate,getCompanyId());
        String path = session.getServletContext().getRealPath("make/xlsprint/tOUTPRODUCT.xlsx");
        FileInputStream InputStream = new FileInputStream(path);
        //h获取
        Workbook workbook = new XSSFWorkbook(InputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Cell cell =sheet.getRow(0).getCell(1);
        //设置表名
        cell.setCellValue(inputDate.replace("-0","年").replace("-","年")+ "月出货表");
        CellStyle[] cellStyles = new CellStyle[8];
        Row row =null;
        row = sheet.getRow(2);
        for (int i = 1; i < 9; i++) {//获取模板中的单元格样式
            cellStyles[i-1] = row.getCell(i).getCellStyle();
        }
        //设置内容
        int rowIndex = 2;

        for (ContractProductVo contractProductVo : contractProductVoList) {
            row = sheet.createRow(rowIndex);
             cell = row.createCell(1);
            cell.setCellValue(contractProductVo.getCustomName());
            cell.setCellStyle(cellStyles[0]);

             cell = row.createCell(2);
            cell.setCellValue(contractProductVo.getContractNo());
            cell.setCellStyle(cellStyles[1]);

             cell = row.createCell(3);
            cell.setCellValue(contractProductVo.getProductNo());
            cell.setCellStyle(cellStyles[2]);

             cell = row.createCell(4);
            cell.setCellValue(contractProductVo.getCnumber());
            cell.setCellStyle(cellStyles[3]);

             cell = row.createCell(5);
            cell.setCellValue(contractProductVo.getFactoryName());
            cell.setCellStyle(cellStyles[4]);

             cell = row.createCell(6);
            cell.setCellValue(new SimpleDateFormat("yyyy-MM").format(contractProductVo.getDeliveryPeriod()));
            cell.setCellStyle(cellStyles[5]);

             cell = row.createCell(7);
            cell.setCellValue(new SimpleDateFormat("yyyy-MM").format(contractProductVo.getShipTime()));
            cell.setCellStyle(cellStyles[6]);

            cell = row.createCell(8);
            cell.setCellValue(contractProductVo.getTradeTerms());
            cell.setCellStyle(cellStyles[7]);

            rowIndex++;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream,response,"出货表.xlsx");

    }

    @RequestMapping(value = "/printExcel",name = "excel到处，自定义样式")
    public void printExcel(String inputDate) throws IOException {

        List<ContractProductVo> contractProductVoList =  contractService.findContractProductVoByShipTime(inputDate,getCompanyId());
        //导出excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("测试");
        //设置列宽
        sheet.setColumnWidth(0,17*256);
        sheet.setColumnWidth(1,27*256);
        sheet.setColumnWidth(2,17*256);
        sheet.setColumnWidth(3,27*256);
        sheet.setColumnWidth(4,17*256);
        sheet.setColumnWidth(5,17*256);
        sheet.setColumnWidth(6,17*256);
        sheet.setColumnWidth(7,17*256);
        sheet.setColumnWidth(8,17*256);
        Row bigTitlerow = sheet.createRow(0);

        //创建单元格   必须先创建   才能给单元格    控制正异常
        for (int i = 0; i < 9; i++) {
            bigTitlerow.createCell(i);
        }
        //设置行高
        bigTitlerow.setHeightInPoints(36);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
        Cell cell1 = bigTitlerow.getCell(1);
        //设置表名
        cell1.setCellValue(inputDate.replace("-0","年").replace("-","年")+ "月出货表");
        cell1.setCellStyle(bigTitle(workbook));
        //设置表头
        Row titleRow = sheet.createRow(1);
        String[] titles = new String[]{"客户","合同号","货号","数量","工厂","工厂交期","货期","贸易条款"};
        for (int i = 1; i < 9; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellStyle(title(workbook));
            cell.setCellValue(titles[i-1]);
        }
        //设置内容
        int rowIndex = 2;
        Row row =null;
        Cell cell = null;
        for (ContractProductVo contractProductVo : contractProductVoList) {
            row = sheet.createRow(rowIndex);
            cell = row.createCell(1);
            cell.setCellValue(contractProductVo.getCustomName());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(2);
            cell.setCellValue(contractProductVo.getContractNo());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(3);
            cell.setCellValue(contractProductVo.getProductNo());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(4);
            cell.setCellValue(contractProductVo.getCnumber());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(5);
            cell.setCellValue(contractProductVo.getFactoryName());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(6);
            cell.setCellValue(new SimpleDateFormat("yyyy-MM").format(contractProductVo.getDeliveryPeriod()));
            cell.setCellStyle(text(workbook));

            cell = row.createCell(7);
            cell.setCellValue(new SimpleDateFormat("yyyy-MM").format(contractProductVo.getShipTime()));
            cell.setCellStyle(text(workbook));

            cell = row.createCell(8);
            cell.setCellValue(contractProductVo.getTradeTerms());
            cell.setCellStyle(text(workbook));

            rowIndex++;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream,response,"出货表.xlsx");

    }

    @RequestMapping(value = "/printExcelMillion",name = "百万数据导出")
    public void printExcelMillion(String inputDate) throws IOException {

        List<ContractProductVo> contractProductVoList =  contractService.findContractProductVoByShipTime(inputDate,getCompanyId());
        //导出excel
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("测试");
        //设置列宽
        sheet.setColumnWidth(0,17*256);
        sheet.setColumnWidth(1,27*256);
        sheet.setColumnWidth(2,17*256);
        sheet.setColumnWidth(3,27*256);
        sheet.setColumnWidth(4,17*256);
        sheet.setColumnWidth(5,17*256);
        sheet.setColumnWidth(6,17*256);
        sheet.setColumnWidth(7,17*256);
        sheet.setColumnWidth(8,17*256);
        Row bigTitlerow = sheet.createRow(0);

        //创建单元格   必须先创建   才能给单元格    控制正异常
        for (int i = 0; i < 9; i++) {
            bigTitlerow.createCell(i);
        }
        //设置行高
        bigTitlerow.setHeightInPoints(36);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
        Cell cell1 = bigTitlerow.getCell(1);
        //设置表名
        cell1.setCellValue(inputDate.replace("-0","年").replace("-","年")+ "月出货表");
        //设置表头
        Row titleRow = sheet.createRow(1);
        String[] titles = new String[]{"客户","合同号","货号","数量","工厂","工厂交期","货期","贸易条款"};
        for (int i = 1; i < 9; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellStyle(title(workbook));
            cell.setCellValue(titles[i-1]);
        }
        //设置内容
        int rowIndex = 2;
        Row row =null;
        Cell cell = null;
        for (ContractProductVo contractProductVo : contractProductVoList) {
            for (int i = 0; i < 3000; i++) {
                row = sheet.createRow(rowIndex);
                cell = row.createCell(1);
                cell.setCellValue(contractProductVo.getCustomName());

                cell = row.createCell(2);
                cell.setCellValue(contractProductVo.getContractNo());

                cell = row.createCell(3);
                cell.setCellValue(contractProductVo.getProductNo());

                cell = row.createCell(4);
                cell.setCellValue(contractProductVo.getCnumber());

                cell = row.createCell(5);
                cell.setCellValue(contractProductVo.getFactoryName());

                cell = row.createCell(6);
                cell.setCellValue(new SimpleDateFormat("yyyy-MM").format(contractProductVo.getDeliveryPeriod()));

                cell = row.createCell(7);
                cell.setCellValue(new SimpleDateFormat("yyyy-MM").format(contractProductVo.getShipTime()));

                cell = row.createCell(8);
                cell.setCellValue(contractProductVo.getTradeTerms());
                rowIndex++;
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        downloadUtil.download(byteArrayOutputStream,response,"出货表.xlsx");

    }





    //大标题的样式
    public CellStyle bigTitle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)16);
        font.setBold(true);//字体加粗
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);                //横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);     //纵向居中
        return style;
    }
    //小标题的样式
    public CellStyle title(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);                //横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);     //纵向居中
        style.setBorderTop(BorderStyle.THIN);              //上细线
        style.setBorderBottom(BorderStyle.THIN);            //下细线
        style.setBorderLeft(BorderStyle.THIN);             //左细线
        style.setBorderRight(BorderStyle.THIN);             //右细线
        return style;
    }

    //文字样式
    public CellStyle text(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short)10);
        style.setFont(font);

//        style.setFillForegroundColor(color);
//        style .setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);//横向居左
        style.setVerticalAlignment(VerticalAlignment.CENTER);     //纵向居中
        style.setBorderTop(BorderStyle.THIN);              //上细线
        style.setBorderBottom(BorderStyle.THIN);            //下细线
        style.setBorderLeft(BorderStyle.THIN);             //左细线
        style.setBorderRight(BorderStyle.THIN);             //右细线

        return style;
    }
}

