package ru.mygradproject.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mygradproject.model.Dish;
import ru.mygradproject.model.Restaurant;
import ru.mygradproject.service.DishService;
import ru.mygradproject.service.RestaurantService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/rest/restaurants";

    private final RestaurantService restaurantService;

    private final DishService dishService;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @GetMapping
    public List<Restaurant> getAll() {                                                          // +++
        return restaurantService.getAll();
    }                   // +++

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }     // +++

    @GetMapping(value = "/by")
    public Restaurant get(@RequestParam("name") String name) {                                  // +++
        return restaurantService.getByName(name);
    }   // +++

    @GetMapping(value = "/todayDishes")
    public Map<Restaurant, List<Dish>> getRestaurantsWithDishes(){
        return dishService.getRestaurantsWithDishes(LocalDate.now());
    }
}
