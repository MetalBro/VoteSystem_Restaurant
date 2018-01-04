package ru.mygradproject.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity{

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "main_course", nullable = false)
    @NotBlank
    @Size(min = 2, max = 200)
    private String mainCourse;

    @Column(name = "soup", nullable = true)
    private String soup;

    @Column(name = "salad", nullable = true)
    private String salad;

    @Column(name = "appetizer", nullable = true)
    private String appetizer;

    @Column(name = "dessert", nullable = true)
    private String dessert;

    @Column(name = "lunch_price", nullable = false)
    private BigDecimal lunch_price;

    public Menu(){

    }

    public Menu(Menu menu){
        this(menu.getId(), menu.getDate(), menu.getMainCourse(), menu.getSoup(), menu.getSalad(), menu.getAppetizer(), menu.getDessert(), menu.getLunch_price());
    }

    public Menu(Integer id, @NotNull LocalDate date, @NotBlank @Size(min = 2, max = 200) String mainCourse, String soup, String salad, String appetizer, String dessert, BigDecimal lunch_price) {
        super(id);
        this.date = date;
        this.mainCourse = mainCourse;
        this.soup = soup;
        this.salad = salad;
        this.appetizer = appetizer;
        this.dessert = dessert;
        this.lunch_price = lunch_price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getSoup() {
        return soup;
    }

    public void setSoup(String soup) {
        this.soup = soup;
    }

    public String getSalad() {
        return salad;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public String getAppetizer() {
        return appetizer;
    }

    public void setAppetizer(String appetizer) {
        this.appetizer = appetizer;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public BigDecimal getLunch_price() {
        return lunch_price;
    }

    public void setLunch_price(BigDecimal lunch_price) {
        this.lunch_price = lunch_price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "date=" + date +
                ", mainCourse=" + mainCourse +
                ", soup=" + soup +
                ", salad=" + salad +
                ", appetizer=" + appetizer +
                ", dessert=" + dessert +
                '}';
    }
}
