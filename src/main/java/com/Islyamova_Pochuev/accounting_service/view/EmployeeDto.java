package com.Islyamova_Pochuev.accounting_service.view;

import java.util.List;

public class EmployeeDto {
    private Integer id;

    private String name;

    private String surname;

    private String patronymic;

    private String post;

    private List<ItemDto> itemsDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public List<ItemDto> getItemsDto() {
        return itemsDto;
    }

    public void setItemsDto(List<ItemDto> itemsDto) {
        this.itemsDto = itemsDto;
    }
}
