package ru.mygradproject.service;

import ru.mygradproject.model.Dish;
import ru.mygradproject.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DishService {

    Dish createOrUpdate(Dish dish, int restaurantId);

    void delete(int id);

    void deleteAllByRestaurant(int restaurantId);

    Dish get(int id, int restaurantId);

    Map<LocalDate, List<Dish>> findActuals(int restaurantId, LocalDate currentDate);

    List<Dish> getByRestaurantAndDate(int restaurantId, LocalDate localDate);

    Map<Restaurant, List<Dish>> getRestaurantsWithDishes(LocalDate currentDate);

}
