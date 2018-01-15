package ru.mygradproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.mygradproject.model.Dish;
import ru.mygradproject.model.Restaurant;
import ru.mygradproject.repository.DishRepository;
import ru.mygradproject.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService{

    private final DishRepository dishRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @CacheEvict(value = {"dishesActuals","dishesByDate"}, allEntries = true)
    @Override
    @Transactional
    public Dish createOrUpdate(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        return dishRepository.save(dish);
    }

    @CacheEvict(value = {"dishesActuals","dishesByDate"}, allEntries = true)
    @Override
    public void delete(int id) {
        dishRepository.deleteById(id);
    }

    @CacheEvict(value = {"dishesActuals","dishesByDate"}, allEntries = true)
    @Override
    public void deleteAllByRestaurant(int restaurantId) {
        dishRepository.deleteAllByRestaurant(restaurantRepository.getOne(restaurantId));
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return dishRepository.findById(id).filter(dish -> dish.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Cacheable("dishesActuals")
    @Override
    public Map<LocalDate, List<Dish>> findActuals(int restaurantId, LocalDate currentDate){
        Map<LocalDate, List<Dish>> dateDishMap = new HashMap<>();
        dateDishMap = dishRepository.getActuals(restaurantId, currentDate).stream()
                .collect(
                        Collectors.groupingBy(Dish::getDate)
                );
        return dateDishMap;
    }

    @Cacheable(value = "dishesForToday")
    @Override
    public Map<Restaurant, List<Dish>> getRestaurantsWithDishes(LocalDate currentDate){
        Map<Restaurant, List<Dish>> restDishMap = new HashMap<>();
        restDishMap = dishRepository.findAllByDate(currentDate).stream()
                .collect(
                        Collectors.groupingBy(Dish::getRestaurant)
                );
        return restDishMap;
    }

    @Cacheable(value = "dishesByDate")
    @Override
    public List<Dish> getByRestaurantAndDate(int restaurantId, LocalDate localDate) {
        return dishRepository.findAllByRestaurantIdAndDate(restaurantId, localDate);
    }
}
