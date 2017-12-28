package ru.mygradproject.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mygradproject.model.Restaurant;
import ru.mygradproject.model.Vote;
import ru.mygradproject.repository.restaurant.RestaurantRepositoryImpl;

import java.util.List;

@Repository
public class VoteRepositoryImpl {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantRepositoryImpl restaurantRepository;

    public Vote get(int id){
        return voteRepository.findById(id).orElse(null);
    }

    public Vote save(Vote vote){
        return voteRepository.save(vote);
    }

    public int delete(int id){
        return voteRepository.delete(id);
    }

    public int countByRestaurant(int restaurantId){
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        return voteRepository.countVotesByRestaurant(restaurant);
    }
}
