package com.Islyamova_Pochuev.accounting_service.excel;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class ExcelController {
    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/excel")
    @Operation(summary = "Создать Excel отчёт")
    public void createExcel(@RequestParam("fileName") String fileName) {
        excelService.createExcel(fileName);
    }

}
