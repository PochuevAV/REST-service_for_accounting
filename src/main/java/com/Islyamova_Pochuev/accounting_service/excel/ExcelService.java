package com.Islyamova_Pochuev.accounting_service.excel;
import com.Islyamova_Pochuev.accounting_service.employees.Employee;
import com.Islyamova_Pochuev.accounting_service.items.Item;
import com.Islyamova_Pochuev.accounting_service.repositories.EmployeeRepository;
import com.Islyamova_Pochuev.accounting_service.repositories.ItemRepository;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    private  final EmployeeRepository employeeRepository;
    private  final ItemRepository itemRepository;

    public ExcelService(EmployeeRepository employeeRepository, ItemRepository itemRepository) {
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
    }

    public void createExcel(String fileName){
       try (HSSFWorkbook workbook = new HSSFWorkbook()){
        HSSFSheet sheet = workbook.createSheet("Список сотрудников");
        HSSFSheet sheetItem = workbook.createSheet("Список имущества");
        HSSFFont headerFont = workbook.createFont();
        HSSFFont font = workbook.createFont();
            CellStyle headerStyle = workbook.createCellStyle();
            CellStyle style = workbook.createCellStyle();
            CellStyle dateStyle = workbook.createCellStyle();
                DataFormat format = workbook.createDataFormat();
                headerStyle.setBorderBottom(BorderStyle.MEDIUM);
                headerStyle.setBorderTop(BorderStyle.MEDIUM);
                headerStyle.setBorderRight(BorderStyle.MEDIUM);
                headerStyle.setBorderLeft(BorderStyle.MEDIUM);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                headerStyle.setVerticalAlignment(VerticalAlignment.TOP);
                headerStyle.setFont(headerFont);
                style.setBorderBottom(BorderStyle.MEDIUM);
                style.setBorderTop(BorderStyle.MEDIUM);
                style.setBorderRight(BorderStyle.MEDIUM);
                style.setBorderLeft(BorderStyle.MEDIUM);
                style.setAlignment(HorizontalAlignment.CENTER);
                style.setVerticalAlignment(VerticalAlignment.TOP);
                style.setFont(font);
                dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
                dateStyle.setBorderBottom(BorderStyle.MEDIUM);
                dateStyle.setBorderTop(BorderStyle.MEDIUM);
                dateStyle.setBorderRight(BorderStyle.MEDIUM);
                dateStyle.setBorderLeft(BorderStyle.MEDIUM);
                dateStyle.setAlignment(HorizontalAlignment.CENTER);
                dateStyle.setVerticalAlignment(VerticalAlignment.TOP);
                dateStyle.setFont(font);
                int width = 20;
                int widthItem = 27;
                sheet.setDefaultColumnWidth(width);
                sheetItem.setDefaultColumnWidth(widthItem);
                headerFont.setFontName("Times New Roman");
                font.setFontName(headerFont.getFontName());
                headerFont.setBold(true);
                createHeader(sheet, headerStyle);
                List<Employee> employeeList = employeeRepository.findAll();
                List<Item> itemList = itemRepository.findAll();
                fillExcel(employeeList, sheet, style);
                createItemHeader(sheetItem, headerStyle);
                fillItemExcel(itemList,  sheetItem, dateStyle, style);
        workbook.write(new FileOutputStream(fileName + ".xls"));
    } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
public void createHeader(HSSFSheet sheet, CellStyle headerStyle) {
    Row header = sheet.createRow(0);
    Cell idHeader = header.createCell(0);
    idHeader.setCellStyle(headerStyle);
    idHeader.setCellValue("id работника");
    Cell nameHeader = header.createCell(1);
    nameHeader.setCellStyle(headerStyle);
    nameHeader.setCellValue("Имя работника");
    Cell surnameHeader = header.createCell(2);
    surnameHeader.setCellStyle(headerStyle);
    surnameHeader.setCellValue("Фамилия работника");
    Cell patronymicHeader = header.createCell(3);
    patronymicHeader.setCellStyle(headerStyle);
    patronymicHeader.setCellValue("Отчество работника");
    Cell postHeader = header.createCell(4);
    postHeader.setCellStyle(headerStyle);
    postHeader.setCellValue("Должность работника");
}

public int fillExcel(List<Employee> employeeList, HSSFSheet sheet, CellStyle style){
    int rowIndex = 1;
    for (Employee employees : employeeList){
        Row row = sheet.createRow(rowIndex);
        Cell cellid = row.createCell(0);
        cellid.setCellStyle(style);
        cellid.setCellValue(employees.getId());
        Cell cellName = row.createCell(1);
        cellName.setCellStyle(style);
        cellName.setCellValue(employees.getName());
        Cell cellSurname = row.createCell(2);
        cellSurname.setCellStyle(style);
        cellSurname.setCellValue(employees.getSurname());
        Cell cellPatronymic = row.createCell(3);
        cellPatronymic.setCellStyle(style);
        cellPatronymic.setCellValue(employees.getPatronymic());
        Cell cellPost = row.createCell(4);
        cellPost.setCellStyle(style);
        cellPost.setCellValue(employees.getPost());
        rowIndex++;
    }
    return rowIndex;
}

    public void createItemHeader(HSSFSheet sheetItem, CellStyle headerStyle) {
        Row header = sheetItem.createRow(0);
        Cell idHeader = header.createCell(0);
        idHeader.setCellStyle(headerStyle);
        idHeader.setCellValue("id имущества");
        Cell employeeidHeader = header.createCell(1);
        employeeidHeader.setCellStyle(headerStyle);
        employeeidHeader.setCellValue("id ответственного работника");
        Cell typeHeader = header.createCell(2);
        typeHeader.setCellStyle(headerStyle);
        typeHeader.setCellValue("Тип имущества");
        Cell costHeader = header.createCell(3);
        costHeader.setCellStyle(headerStyle);
        costHeader.setCellValue("Стоимость имущества");
        Cell dateHeader = header.createCell(4);
        dateHeader.setCellStyle(headerStyle);
        dateHeader.setCellValue("Дата получения имущества");
    }

    public int fillItemExcel(List <Item> itemList, HSSFSheet sheetItem, CellStyle dateStyle, CellStyle style){
        int rowIndex = 1;
        for (Item item : itemList){
            Row row = sheetItem.createRow(rowIndex);
            Cell cellid = row.createCell(0);
            cellid.setCellStyle(style);
            cellid.setCellValue(item.getId());
            Cell cellidEmp = row.createCell(1);
            cellidEmp.setCellStyle(style);
            cellidEmp.setCellValue(item.getEmployee().getId());
            Cell cellType = row.createCell(2);
            cellType.setCellStyle(style);
            cellType.setCellValue(item.getType());
            Cell cellCost = row.createCell(3);
            cellCost.setCellStyle(style);
            cellCost.setCellValue(item.getCost());
            Cell cellDate = row.createCell(4);
            cellDate.setCellStyle(dateStyle);
            cellDate.setCellValue(item.getCreateDate());
            rowIndex++;
        }
        return rowIndex;
    }
}
