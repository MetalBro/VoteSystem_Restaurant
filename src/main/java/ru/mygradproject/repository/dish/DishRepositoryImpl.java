package ru.mygradproject.repository.dish;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mygradproject.model.Dish;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DishRepositoryImpl {

    @Autowired
    private DishRepository dishRepository;

    public Dish get(int id){
        return dishRepository.findById(id).orElse(null);
    }

    public Dish save(Dish menu){
        return dishRepository.save(menu);
    }

    public int delete(int id){
        return dishRepository.delete(id);
    }

    Map<LocalDate, List<Dish>> findActuals(int restaurantId, LocalDate currentDate){
        Map<LocalDate, List<Dish>> dateDishMap = new HashMap<>();
        dateDishMap = dishRepository.getActuals(restaurantId, currentDate).stream()
                .collect(
                        Collectors.groupingBy(Dish::getDate)
                );
        return dateDishMap;
    }
}
