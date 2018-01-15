package ru.mygradproject.web.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mygradproject.model.Restaurant;
import ru.mygradproject.service.DishService;
import ru.mygradproject.service.RestaurantService;

import java.net.URI;

@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {

    static final String REST_URL = "/rest/admin/restaurants";

    private final RestaurantService restaurantService;

    private final DishService dishService;

    @Autowired
    public RestaurantAdminRestController(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {                                            // +++
        restaurantService.delete(id);
    }           // +++

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {  // +++
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant) {                                    // +++
        restaurantService.update(restaurant);
    }       // +++
}
