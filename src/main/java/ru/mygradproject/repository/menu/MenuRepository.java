package ru.mygradproject.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mygradproject.model.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Menu save(Menu menu);

    @Override
    Optional<Menu> findById(Integer integer);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.date ASC")
    List<Menu> findAllByRestaurantId(Integer restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId AND m.date>=:currentDate ORDER BY m.date ASC")
    List<Menu> findActuals(int restaurantId, LocalDate currentDate);
//    List<Menu> findByRestaurantIdAndDateGreaterThanEqual(int restaurantId, LocalDate date);
}
