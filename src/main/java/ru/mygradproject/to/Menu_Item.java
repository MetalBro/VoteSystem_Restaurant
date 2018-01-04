package ru.mygradproject.to;

import java.math.BigDecimal;

public class Menu_Item {

    private final Integer dishId;

    private final String dishName;

    private final BigDecimal price;

    public Menu_Item(Integer dishId, String dishName, BigDecimal price) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.price = price;
    }

    public Integer getDishId() {
        return dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
