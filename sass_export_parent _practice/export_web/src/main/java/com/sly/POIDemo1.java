package com.sly;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class POIDemo1 {

    public static void main(String[] args) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("测试");
        sheet.setColumnWidth(0,17*256);

        XSSFRow row = sheet.createRow(0);

        XSSFCell cell = row.createCell(0);

        cell.setCellValue("111");

        workbook.write(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\a.xlsx"));

        workbook.close();
    }
}
