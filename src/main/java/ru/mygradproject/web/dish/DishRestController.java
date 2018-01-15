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

    @Autowired
    public DishRestController(DishService dishService) {
        this.dishService = dishService;
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

    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable("restaurantId") int restaurantId, @PathVariable("id") int id) {       // +++
        return dishService.get(id, restaurantId);
    }

}
