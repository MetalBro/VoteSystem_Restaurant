package ru.mygradproject.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mygradproject.model.Restaurant;
import ru.mygradproject.service.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/rest/restaurants";

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAll() {                                                          // +++
        return restaurantService.getAll();
    }                   // +++

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }     // +++

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {                                            // +++
        restaurantService.delete(id);
    }           // +++

    @GetMapping(value = "/by")
    public Restaurant get(@RequestParam("name") String name) {                                  // +++
        return restaurantService.getByName(name);
    }   // +++

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {  // +++
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
//        restaurantService.update(restaurant, id);
//    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant) {                                    // +++
        restaurantService.update(restaurant);
    }       // +++

}
