package ru.mygradproject.service;

import ru.mygradproject.model.User;
import ru.mygradproject.model.Vote;
import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote save(int restaurantId, int userId);

    Vote save(int restaurantId, int userId, LocalDate localDate);

    void delete(int userId, LocalDate localDate);

    Vote get(int userId, LocalDate localDate);

    List<Vote> findAllByDateBetweenAndRestaurantId(LocalDate startDate, LocalDate endDate, int restaurantId);

    List<Vote> findAllByUserId(int userId);

    List<Vote> findAllByRestaurantAndDate(int restaurantId, LocalDate localDate);

    List<User> findUsersForRestaurantAndDate(int restaurantId, LocalDate localDate);

    long countAllByRestaurantId(int restaurantId);

    long countAll();

}
