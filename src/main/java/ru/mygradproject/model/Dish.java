package ru.mygradproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    public Dish(){
    }

    public Dish(Dish dish){
        this(dish.getId(), dish.getName(), dish.getPrice(), dish.getRestaurant(), dish.getDate());
    }

    public Dish(Integer id, String name, BigDecimal price, Restaurant restaurant, @NotNull LocalDate date) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + id +
                ", restaurantId=" + restaurant.getId() +
                ", name='" + name +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
