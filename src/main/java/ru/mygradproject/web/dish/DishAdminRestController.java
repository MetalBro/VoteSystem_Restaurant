package ru.mygradproject.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mygradproject.model.Dish;
import ru.mygradproject.service.DishService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController {

    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/dishes";

    private final DishService dishService;

    @Autowired
    public DishAdminRestController(DishService dishService) {
        this.dishService = dishService;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        dishService.delete(id);
    }

    @DeleteMapping
    public void deleteAllByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        dishService.deleteAllByRestaurant(restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        Dish created = dishService.createOrUpdate(dish, restaurantId);
        Map<String, Integer> uriParams = new HashMap<>();
        uriParams.put("restaurantId", restaurantId);
        uriParams.put("id", created.getId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(uriParams).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        dishService.createOrUpdate(dish, restaurantId);
    }
}
