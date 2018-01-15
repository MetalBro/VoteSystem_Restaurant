package ru.mygradproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.mygradproject.model.User;
import ru.mygradproject.model.Vote;
import ru.mygradproject.model.VotePK;
import ru.mygradproject.repository.RestaurantRepository;
import ru.mygradproject.repository.UserRepository;
import ru.mygradproject.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService{

    private static final LocalTime deadLine = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @CacheEvict(value = {"votesRestaurantCount", "votesAllCount", "votesRestaurantDate"}, allEntries = true)
    @Override
    public Vote save(int restaurantId, int userId) {
        if (!canVote()) return null;
        else {
            Vote vote = new Vote(userRepository.getOne(userId), restaurantRepository.getOne(restaurantId), LocalDate.now());
            return voteRepository.save(vote);
        }
    }

    @CacheEvict(value = {"votesRestaurantCount", "votesAllCount", "votesRestaurantDate"}, allEntries = true)
    @Override
    public Vote save(int restaurantId, int userId, LocalDate localDate) {
        if (!canVote(localDate)) return null;
        else {
            Vote vote = new Vote(userRepository.getOne(userId), restaurantRepository.getOne(restaurantId), localDate);
            return voteRepository.save(vote);
        }
    }

    @CacheEvict(value = {"votesRestaurantCount", "votesAllCount", "votesRestaurantDate"}, allEntries = true)
    @Override
    public void delete(int userId, LocalDate localDate) {
        voteRepository.delete(userId, localDate);
    }

    @Override
    public Vote get(int userId, LocalDate localDate) {
        return voteRepository.getByUserIdAndDate(userId, localDate);
    }

    @Override
    public List<Vote> findAllByDateBetweenAndRestaurantId(LocalDate startDate, LocalDate endDate, int restaurantId) {
        Assert.notNull(startDate, "startDateTime must not be null");
        Assert.notNull(endDate, "endDateTime  must not be null");
        return voteRepository.findAllByDateBetweenAndRestaurantId(startDate, endDate, restaurantId);
    }

    @Override
    public List<Vote> findAllByUserId(int userId) {
        return voteRepository.findAllByUserId(userId);
    }

    @Cacheable(value = "votesRestaurantCount")
    @Override
    public long countAllByRestaurantId(int restaurantId) {
        return voteRepository.countAllByRestaurantId(restaurantId);
    }

    @Cacheable(value = "votesRestaurantDate")
    @Override
    public List<Vote> findAllByRestaurantAndDate(int restaurantId, LocalDate localDate) {
        return voteRepository.findAllByRestaurantAndDate(restaurantId, localDate);
    }

    @Override
    public List<User> findUsersForRestaurantAndDate(int restaurantId, LocalDate localDate) {
        return userRepository.getByRestaurantAndDate(restaurantId, localDate);
    }

    @Cacheable(value = "votesAllCount")
    @Override
    public long countAll() {
        return voteRepository.count();
    }

    private boolean canVote(){
        return LocalTime.now().isBefore(deadLine);
    }

    private boolean canVote(LocalDate localDate){
        return LocalDateTime.now().isBefore(LocalDateTime.of(localDate, deadLine));
    }
}
