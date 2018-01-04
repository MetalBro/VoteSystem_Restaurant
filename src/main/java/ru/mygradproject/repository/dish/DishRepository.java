package ru.mygradproject.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mygradproject.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Override
    Optional<Dish> findById(Integer integer);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> findAllByRestaurantIdAndDate(int restaurantId, LocalDate localDate);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.date>=:currentDate ORDER BY d.date ASC")
    List<Dish> getActuals(int restaurantId, LocalDate currentDate);

}
