package ru.mygradproject.repository.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mygradproject.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepositoryImpl {

    @Autowired
    private MenuRepository menuRepository;

    public Menu get(int id){
        return menuRepository.findById(id).orElse(null);
    }

    public Menu save(Menu menu){
        return menuRepository.save(menu);
    }

    public int delete(int id){
        return menuRepository.delete(id);
    }

//    public List<Menu> getAllByRestaurantId(int id){
//        return menuRepository.findAllByRestaurantId(id);
//    }

    public List<Menu> getActuals(int restaurantId){
        return menuRepository.findActuals(restaurantId, LocalDate.now());
//        return menuRepository.findByRestaurantIdAndDateGreaterThanEqual(restaurantId, LocalDate.now());
    }
}
