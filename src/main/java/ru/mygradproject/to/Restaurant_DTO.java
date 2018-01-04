package ru.mygradproject.to;

import ru.mygradproject.model.Restaurant;

public class Restaurant_DTO {

    private final Restaurant restaurant;

    private final Boolean editable;  // It is used for the functions of adding/removing/editing depending on the user USER (=false) or ADMIN(=true).

    public Restaurant_DTO(Restaurant restaurant, Boolean editable) {
        this.restaurant = restaurant;
        this.editable = editable;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Boolean isEditable() {
        return editable;
    }
}
