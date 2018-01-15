package ru.mygradproject.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mygradproject.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    User save(User user);

    @Override
    Optional<User> findById(Integer id);

    @Override
    List<User> findAll(Sort sort);

    User getByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH Vote v ON u.id=v.primaryKey.user.id WHERE v.restaurant.id=:restaurantId AND v.primaryKey.date=:date")
    List<User> getByRestaurantAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate localDate);
}
