package ru.mygradproject.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.mygradproject.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    Optional<Restaurant> findById(Integer integer);

    @Override
    List<Restaurant> findAll(Sort sort);

    Restaurant findByName(String name);
}