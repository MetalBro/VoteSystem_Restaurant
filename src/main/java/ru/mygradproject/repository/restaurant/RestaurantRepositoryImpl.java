package ru.mygradproject.repository.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.mygradproject.model.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");
//    private static final Sort SORT_RATING = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant save(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public int delete(int id){
        return restaurantRepository.delete(id);
    }

    public Restaurant get(int id){
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll(){
        return restaurantRepository.findAll(SORT_NAME);
    }

    public Restaurant getByName(String name){
        return restaurantRepository.findByName(name);
    }
}
