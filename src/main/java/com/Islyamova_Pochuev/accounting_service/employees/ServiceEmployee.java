package com.Islyamova_Pochuev.accounting_service.employees;

import com.Islyamova_Pochuev.accounting_service.items.Item;
import com.Islyamova_Pochuev.accounting_service.repositories.EmployeeRepository;
import com.Islyamova_Pochuev.accounting_service.view.EmployeeDto;
import com.Islyamova_Pochuev.accounting_service.view.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServiceEmployee {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public ServiceEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void add(EmployeeDto employeeDto) {
        Employee employee= new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setPost(employeeDto.getPost());

        Set<Item> itemList = new HashSet<>();
        for (ItemDto itemDto : employeeDto.getItemsDto()){
            Item item = new Item();
            item.setId(itemDto.getId());
            item.setEmployee(employee);
            item.setType(itemDto.getType());
            item.setCost(itemDto.getCost());
            item.setCreateDate(itemDto.getCreateDate());
            itemList.add(item);
        }
        employee.setItems(itemList);
        employeeRepository.save(employee);
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
    public List<EmployeeDto> getList() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee employee : employeeList){
            EmployeeDto dto = new EmployeeDto();
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            dto.setSurname(employee.getSurname());
            dto.setPatronymic(employee.getPatronymic());
            dto.setPost(employee.getPost());

            List<ItemDto> itemDtoList = new ArrayList<>();

            for (Item item : employee.getItems()){
                ItemDto itemDto = new ItemDto();
                itemDto.setId(item.getId());
                itemDto.setEmployee(item.getEmployee().getId());
                itemDto.setType(item.getType());
                itemDto.setCost(item.getCost());
                itemDto.setCreateDate(item.getCreateDate());
                itemDtoList.add(itemDto);
            }
            dto.setItemsDto(itemDtoList);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void edit(EmployeeDto employeeDto){
        Employee editEmployee = employeeRepository.getReferenceById(employeeDto.getId());
        editEmployee.setName(employeeDto.getName());
        editEmployee.setSurname(employeeDto.getSurname());
        editEmployee.setPatronymic(employeeDto.getPatronymic());
        editEmployee.setPost(employeeDto.getPost());
        Set<Item> itemSet = new HashSet<>();
        for (ItemDto itemDto : employeeDto.getItemsDto()){
            Item item = new Item();
            item.setId(itemDto.getId());
            item.setEmployee(editEmployee);
            item.setType(itemDto.getType());
            item.setCost(itemDto.getCost());
            item.setCreateDate(itemDto.getCreateDate());
            itemSet.add(item);
        }
        editEmployee.setItems(itemSet);
        employeeRepository.save(editEmployee);
    }
}
