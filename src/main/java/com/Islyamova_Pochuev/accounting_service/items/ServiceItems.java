package com.Islyamova_Pochuev.accounting_service.items;

import com.Islyamova_Pochuev.accounting_service.employees.Employee;
import com.Islyamova_Pochuev.accounting_service.repositories.EmployeeRepository;
import com.Islyamova_Pochuev.accounting_service.repositories.ItemRepository;
import com.Islyamova_Pochuev.accounting_service.view.EmployeeDto;
import com.Islyamova_Pochuev.accounting_service.view.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServiceItems {

    private final ItemRepository itemRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ServiceItems(ItemRepository itemRepository, EmployeeRepository employeeRepository) {
        this.itemRepository = itemRepository;
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

    public void delete(Integer id) {
        itemRepository.deleteById(id);
    }

    public List<ItemDto> getList() {
        List<Item> itemList = itemRepository.findAll();
        List<ItemDto> dtoList = new ArrayList<>();
        for (Item item : itemList) {
            ItemDto dto = new ItemDto();
            dto.setId(item.getId());
            dto.setEmployee(item.getEmployee().getId());
            dto.setType(item.getType());
            dto.setCost(item.getCost());
            dto.setCreateDate(item.getCreateDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void edit(EmployeeDto employeeDto) {
        Employee editEmployee = employeeRepository.getReferenceById(employeeDto.getId());
        Set<Item> itemSet = new HashSet<>();
        for (ItemDto itemDto : employeeDto.getItemsDto()) {
            Item item = new Item();
            item.setId(itemDto.getId());
            item.setEmployee(editEmployee);
            item.setType(itemDto.getType());
            item.setCost(itemDto.getCost());
            item.setCreateDate(itemDto.getCreateDate());
            itemSet.add(item);
            itemRepository.save(item);
        }
    }
}
