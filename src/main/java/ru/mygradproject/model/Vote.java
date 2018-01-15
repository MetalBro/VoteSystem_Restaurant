package ru.mygradproject.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
public class Vote {

    @EmbeddedId
    private VotePK primaryKey;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote(){
    }

    public Vote(@NotNull User user, @NotNull Restaurant restaurant, @NotNull LocalDate date) {
        this.primaryKey = new VotePK(user, date);
        this.restaurant = restaurant;
    }


    public VotePK getPrimaryKey() {
        return primaryKey;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + primaryKey.getUser().getId() +
                ", restaurant=" + restaurant +
                ", date=" + primaryKey.getDate() +
                '}';
    }
}
