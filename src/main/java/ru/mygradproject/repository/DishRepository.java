package ru.mygradproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mygradproject.model.Dish;
import ru.mygradproject.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Override
    @Transactional
    Dish save(Dish dish);

    @Override
    Optional<Dish> findById(Integer integer);

    @Transactional
    void deleteAllByRestaurant(Restaurant restaurant);

    List<Dish> findAllByDate(LocalDate localDate);

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.restaurant.id=:restaurantId AND d.date=:currentDate")
    List<Dish> findAllByRestaurantIdAndDate(@Param("restaurantId")int restaurantId,@Param("currentDate") LocalDate localDate);

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.restaurant.id=:restaurantId AND d.date>=:currentDate ORDER BY d.date ASC")
    List<Dish> getActuals(@Param("restaurantId") int restaurantId,@Param("currentDate") LocalDate currentDate);

}
