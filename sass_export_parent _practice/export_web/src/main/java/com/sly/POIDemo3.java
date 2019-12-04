package com.sly;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class POIDemo3 {

    public static void main(String[] args) throws Exception {

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

        //创建表名样式
        CellStyle cellStyle = workbook.createCellStyle();
        //水平对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直对齐方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //字体  宋体16号加粗
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);//加粗
        cellStyle.setFont(font);


        //创建表头样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        //水平对齐方式
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        //垂直对齐方式
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setBorderBottom(BorderStyle.THIN);
        cellStyle1.setBorderLeft(BorderStyle.THIN);
        cellStyle1.setBorderRight(BorderStyle.THIN);
        cellStyle1.setBorderTop(BorderStyle.THIN);
        //字体  宋体16号加粗
        Font font1 = workbook.createFont();
        font1.setFontName("黑体");
        font1.setFontHeightInPoints((short) 12);
        font1.setBold(false);
        cellStyle1.setFont(font);

        //创建单元格   必须先创建   才能给单元格    控制正异常
        for (int i = 0; i < 9; i++) {
            bigTitlerow.createCell(i);
        }
        //设置行高
        bigTitlerow.setHeightInPoints(36);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));
        Cell cell1 = bigTitlerow.getCell(1);
        //设置表名
        cell1.setCellValue("2012年8月出货表");
        cell1.setCellStyle(cellStyle);
        //设置表头
        Row titleRow = sheet.createRow(1);
        Cell cell = titleRow.createCell(1);
        cell.setCellValue("客户");
        cell.setCellStyle(cellStyle1);

        Cell cell2 = titleRow.createCell(2);
        cell.setCellValue("合同号");
        cell.setCellStyle(cellStyle1);

        Cell cell3 = titleRow.createCell(3);
        cell.setCellValue("");
        cell.setCellStyle(cellStyle1);

        Cell cell4 = titleRow.createCell(4);
        cell.setCellValue("客户");
        cell.setCellStyle(cellStyle1);

        Cell cell5 = titleRow.createCell(5);
        cell.setCellValue("客户");
        cell.setCellStyle(cellStyle1);

        Cell cell6 = titleRow.createCell(6);
        cell.setCellValue("客户");
        cell.setCellStyle(cellStyle1);

        Cell cell7 = titleRow.createCell(7);
        cell.setCellValue("客户");
        cell.setCellStyle(cellStyle1);

        Cell cell8 = titleRow.createCell(8);
        cell.setCellValue("客户");
        cell.setCellStyle(cellStyle1);


        workbook.write(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\a.xlsx"));

        workbook.close();
    }
}
