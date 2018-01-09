package ru.mygradproject.service;

import ru.mygradproject.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getByName(String name);
}
