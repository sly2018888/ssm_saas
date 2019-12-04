package com.sly;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class POIDemo2 {

    public static void main(String[] args) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("C:\\Users\\Administrator\\Desktop\\a.xlsx"));
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        XSSFRow row = sheetAt.getRow(0);
        XSSFCell cell = row.getCell(0);
        String stringCellValue = cell.getStringCellValue();
        System.out.println(stringCellValue);
        workbook.close();


    }
}
