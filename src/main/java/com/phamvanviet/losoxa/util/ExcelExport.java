package com.phamvanviet.losoxa.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Component
public class ExcelExport {
    @Autowired
    StyleExcel styleExcel;

    public void setContentHeader(XSSFSheet sheet, int rowIndex, XSSFCell cell, List<String> headerColumns) {
        CellStyle cellStyle = styleExcel.headerCell(sheet);
        XSSFRow row = sheet.createRow(rowIndex);
        for (int i=0;i<headerColumns.size();i++){
            cell = row.createCell((short) i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(headerColumns.get(i));
        }
    }

    public void setContentTable(List<Object> contents, XSSFRow row,XSSFSheet sheet) {
        for(int i=0;i<contents.size();i++){
            CellStyle cellStyle = styleExcel.contentCell(sheet);
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            if(contents.get(i) instanceof  List<?>){
                StringBuilder sb = new StringBuilder();
                for (Object name : (List<Object>) contents.get(i)){
                    sb.append(name.toString()).append("\n");
                }
                cell.setCellValue(sb.toString());
            }else{
                cell.setCellValue(contents.get(i).toString());
            }
        }
    }


    // Auto resize column width
    public  void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    public void setTitle(XSSFCell cell, XSSFSheet sheet,
                         String content, CellRangeAddress cellRangeAddress, Integer indexRow, Integer indexCell){
        if(StringUtils.isNotBlank(content)){
            XSSFRow rowTitle = sheet.createRow(indexRow);
            cell = rowTitle.createCell(indexCell);
            cell.setCellStyle(styleExcel.contentTitle(sheet));
            cell.setCellValue(content);
            sheet.addMergedRegion(cellRangeAddress);
        }
    }
    // Create output file
    public  void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        File file = new File(StringUtils.substringBeforeLast(excelFilePath, "/"));
        if (!file.exists()) {
            file.mkdirs();
        }
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
