package com.Islyamova_Pochuev.accounting_service.controller;

import com.Islyamova_Pochuev.accounting_service.employees.Employee;
import com.Islyamova_Pochuev.accounting_service.employees.ServiceEmployee;
import com.Islyamova_Pochuev.accounting_service.items.Item;
import com.Islyamova_Pochuev.accounting_service.items.ServiceItems;
import com.Islyamova_Pochuev.accounting_service.view.EmployeeDto;
import com.Islyamova_Pochuev.accounting_service.view.ItemDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/items")
public class ItemController {
  private final ServiceEmployee serviceEmployee;
  private final ServiceItems serviceItems;

    public ItemController(ServiceEmployee serviceEmployee, ServiceItems serviceItems) {
        this.serviceEmployee = serviceEmployee;
        this.serviceItems = serviceItems;
    }
   @PostMapping("/items")
    @Operation(summary = "Добавить новую материальную ценность")
    public void createItem(@RequestBody EmployeeDto employeeDto){
        serviceItems.add(employeeDto);
    }

    @DeleteMapping("/items")
    @Operation(summary = "Удалить материальную ценность")
    public void deleteItem(@RequestParam Integer id){
        serviceItems.delete(id);
    }

    @GetMapping("/items")
    @Operation(summary = "Вывести список всех материальных ценностей")
    public List<ItemDto> getItem(){
      return serviceItems.getList();
    }

    @PutMapping("/items")
    @Operation(summary = "Отредактировать данные единицы материальной ценности")
    public void edit(@RequestBody EmployeeDto employeeDto){
      serviceItems.edit(employeeDto);
    }
}
