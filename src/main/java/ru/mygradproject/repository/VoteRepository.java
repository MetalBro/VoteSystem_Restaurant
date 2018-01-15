package ru.mygradproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mygradproject.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.primaryKey.user.id=:userId AND v.primaryKey.date=:localDate")
    void delete(@Param("userId") int userId, @Param("localDate") LocalDate localDate);

    @Override
    @Transactional
    Vote save(Vote vote);


    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant LEFT JOIN FETCH v.primaryKey.user WHERE v.primaryKey.user.id=:userId AND v.primaryKey.date=:currentDate")
    Vote getByUserIdAndDate(@Param("userId") int userId, @Param("currentDate") LocalDate localDate);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant  WHERE v.restaurant.id=:restaurantId AND v.primaryKey.date BETWEEN :startDate AND :endDate")
    List<Vote> findAllByDateBetweenAndRestaurantId(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant JOIN FETCH User u ON v.primaryKey.user.id=u.id WHERE v.primaryKey.user.id=:userId")
    List<Vote> findAllByUserId(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant JOIN FETCH v.primaryKey.user WHERE v.restaurant.id=:restaurantId AND v.primaryKey.date=:date")
    List<Vote> findAllByRestaurantAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate localDate);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.restaurant.id=:restaurantId")
    long countAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Override
    long count();
}
