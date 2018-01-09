package ru.mygradproject.web.dish;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mygradproject.model.Dish;
import ru.mygradproject.service.DishService;
import ru.mygradproject.service.RestaurantService;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    static final String REST_URL = "/rest/restaurants/{restaurantId}/dishes";

    private final DishService dishService;

    private final RestaurantService restaurantService;

    @Autowired
    public DishRestController(DishService dishService, RestaurantService restaurantService) {
        this.dishService = dishService;
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "/actuals")
    public Map<LocalDate, List<Dish>> getActuals(@PathVariable("restaurantId") int restaurantId){       // +++
        LocalDate currentDate = LocalDate.now();
        return dishService.findActuals(restaurantId, currentDate);
    }

    @GetMapping(value = "/by")
    public List<Dish> getThisDay(@PathVariable("restaurantId") int restaurantId,                        // +++
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate){
        return dishService.getByRestaurantAndDate(restaurantId, localDate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        dishService.delete(id);
    }                           // +++

    @DeleteMapping
    public void deleteAllByRestaurantId(@PathVariable("restaurantId") int restaurantId) {               // +++
        dishService.deleteAllByRestaurant(restaurantId);
    }

    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable("restaurantId") int restaurantId, @PathVariable("id") int id) {       // +++
        return dishService.get(id, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {    // +++
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
    public void update(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {            // +++
        dishService.createOrUpdate(dish, restaurantId);
    }


}
