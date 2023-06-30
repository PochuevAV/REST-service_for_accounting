package com.Islyamova_Pochuev.accounting_service.controller;

import com.Islyamova_Pochuev.accounting_service.employees.Employee;
import com.Islyamova_Pochuev.accounting_service.employees.ServiceEmployee;
import com.Islyamova_Pochuev.accounting_service.items.ServiceItems;
import com.Islyamova_Pochuev.accounting_service.view.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final ServiceEmployee serviceEmployee;
    private final ServiceItems serviceItems;

    public EmployeeController(ServiceEmployee serviceEmployee, ServiceItems serviceItems) {
        this.serviceEmployee = serviceEmployee;
        this.serviceItems = serviceItems;
    }

    @PostMapping("/employee")
    @Operation(summary = "Добавить нового сотрудника")
    public void createEmployee(@RequestBody EmployeeDto employeeDto) {
        serviceEmployee.add(employeeDto);
    }

    @DeleteMapping("/employee")
    @Operation(summary = "Удалить сотрудника")
    public void deleteEmployee(@RequestParam Integer id) {
        serviceEmployee.delete(id);
    }

    @GetMapping("/employee")
    @Operation(summary = "Вывести список всех сотрудников")
    public List<EmployeeDto> getEmployee() {
        return serviceEmployee.getList();
    }

    @PutMapping("/employee")
    @Operation(summary = "Отредактировать данные сотрудника")
    public void edit(@RequestBody EmployeeDto employeeDto) {
        serviceEmployee.edit(employeeDto);
    }
}