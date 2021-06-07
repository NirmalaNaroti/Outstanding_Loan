package com.fragma.service;

import com.fragma.dto.Outstanding_Loan;
import com.fragma.dto.MainDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class ExcelFileCreator {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelFileCreator.class);

    XSSFWorkbook workbook = new XSSFWorkbook();

    DecimalFormat df = new DecimalFormat("#,###.00");

    public void createAllSheets(String excelFileLocation, MainDto mainDto) throws Exception {

         createDataTemplateSheet(mainDto, "OutStanding_Loan");


        FileOutputStream out = new FileOutputStream(excelFileLocation);
        this.workbook.write(out);
        out.close();
        LOG.info(" Excel file written successfully on disk at :" + excelFileLocation);
    }

    private void createDataTemplateSheet(MainDto mainDto, String sheetName) throws Exception {

        LOG.info("***** executing createDataTemplateSheet ****** ");


        Font headingFont = workbook.createFont();
        headingFont.setBold(true);

        XSSFColor orange = new XSSFColor(new java.awt.Color(182, 207, 242));

        XSSFCellStyle headingCellStyle = workbook.createCellStyle();

        headingCellStyle.setFont(headingFont);
        headingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headingCellStyle.setFillForegroundColor(orange);
        headingCellStyle.setBorderBottom(BorderStyle.THIN);
        headingCellStyle.setBorderLeft(BorderStyle.THIN);
        headingCellStyle.setBorderRight(BorderStyle.THIN);
        headingCellStyle.setBorderTop(BorderStyle.THIN);
        headingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headingCellStyle.setWrapText(true);

        XSSFColor lightOrange = new XSSFColor(new java.awt.Color(255, 216, 151));

        XSSFCellStyle MainHeadingCellStyle = workbook.createCellStyle();

        MainHeadingCellStyle.setFont(headingFont);
        MainHeadingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        MainHeadingCellStyle.setFillForegroundColor(lightOrange);
        MainHeadingCellStyle.setBorderBottom(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderLeft(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderRight(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderTop(BorderStyle.THIN);
        MainHeadingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        MainHeadingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        MainHeadingCellStyle.setWrapText(true);

        CellStyle bordersOnly = workbook.createCellStyle();
        bordersOnly.setBorderBottom(BorderStyle.THIN);
        bordersOnly.setBorderLeft(BorderStyle.THIN);
        bordersOnly.setBorderRight(BorderStyle.THIN);
        bordersOnly.setBorderTop(BorderStyle.THIN);
        bordersOnly.setAlignment(HorizontalAlignment.CENTER);
        bordersOnly.setVerticalAlignment(VerticalAlignment.CENTER);


        Sheet sheet = workbook.createSheet(sheetName);


        int rowNum=0;

        Row headingRow = sheet.createRow(rowNum++);
        headingRow.setHeight((short) 900);

        int headingColmIndx = 0;


        createCellAddData(headingRow, headingColmIndx++, "Account Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Branch Code", headingCellStyle);

        createCellAddData(headingRow, headingColmIndx++, "Customer ID", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Product Code", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Product Catagory", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Value Date", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Maturity Date", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Amount Financed", headingCellStyle);

        createCellAddData(headingRow, headingColmIndx++, "Currency ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Primary Applicant Name", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Liquidation Mode ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Account Status ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Alt Acc No ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Partial Liquidation ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Field Char 1  ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Field Char 2  ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Field Char 3  ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Bill Ref No  ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Amt Available ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Fig Ref No", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "LC Number", headingCellStyle);


        for (Map.Entry<Integer, Outstanding_Loan> tdEntry : mainDto.getMap().entrySet()) {

            Row row = sheet.createRow(rowNum++);
            int cell = 0;


            createCellAddData(row, cell++, tdEntry.getValue().getAccountNumber(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBranchCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getCustomerId(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getProductCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getProductCatagory(), bordersOnly);


            createCellAddData(row, cell++, tdEntry.getValue().getValueDate(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getMaturityDate(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAmountFinanced(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getCurrency(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getPrimaryApplicantName(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getLiquidationMode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAccountStatus(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAltAccNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getPartialLiquidation(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getFieldChar1(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getFieldChar2(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getFieldChar3(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBillRefNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getAmtAvailable(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getFigRefNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getLcNumber(), bordersOnly);

        }

        for (int i = 0; i <= sheet.getRow(1).getLastCellNum(); i++) {

            sheet.autoSizeColumn(i);
            int columnWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, columnWidth + 1000);
        }
    }



    public void createCellAddData(Row row, int cellNo, String cellValue, CellStyle cellStyle) {
        Cell cell = row.createCell(cellNo);
        cell.setCellValue(cellValue);
        cell.setCellStyle(cellStyle);
    }

}