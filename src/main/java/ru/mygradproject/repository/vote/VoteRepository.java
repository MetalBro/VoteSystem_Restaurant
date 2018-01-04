package ru.mygradproject.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mygradproject.model.Restaurant;
import ru.mygradproject.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Override
    Optional<Vote> findById(Integer integer);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId")
    List<Vote> findAllByRestaurantId(Integer restaurantId);

//    @Query
    List<Vote> findAllByDateBetweenAndRestaurantId(LocalDate startDate, LocalDate endDate, int restaurantId);

    List<Vote> findAllByUserId(int userId);

    int countVotesByRestaurant(Restaurant restaurant);
}
