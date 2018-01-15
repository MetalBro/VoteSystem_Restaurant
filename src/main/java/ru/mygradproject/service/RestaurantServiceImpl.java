package ru.mygradproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.mygradproject.model.Restaurant;
import ru.mygradproject.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(SORT_NAME);
    }

    @Override
    public Restaurant getByName(String name) {
        return restaurantRepository.findByName(name);
    }
}
