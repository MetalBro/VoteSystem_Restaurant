package ru.mygradproject.to;

import ru.mygradproject.model.Dish;

import java.util.List;

public class Menu_DTO {

    private final List<Dish> dishes;

    private final Boolean editable; // It is used for the functions of adding/removing/editing depending on the user USER (=false) or ADMIN(=true).

    public Menu_DTO(List<Dish> dishes, Boolean editable) {
        this.dishes = dishes;
        this.editable = editable;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Boolean getEditable() {
        return editable;
    }
}