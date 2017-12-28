package ru.mygradproject.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    public Menu(){

    }

    public Menu(Menu menu){
        this(menu.getId(), menu.getDate(), menu.getRestaurant(), menu.getMainCourse(), menu.getSoup(), menu.getSalad(), menu.getAppetizer(), menu.getDessert());
    }

    public Menu(Integer id, @NotNull LocalDate date, @NotNull Restaurant restaurant, @NotBlank @Size(min = 2, max = 200) String mainCourse, String soup, String salad, String appetizer, String dessert) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.mainCourse = mainCourse;
        this.soup = soup;
        this.salad = salad;
        this.appetizer = appetizer;
        this.dessert = dessert;
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

    @Override
    public String toString() {
        return "Menu{" +
                "date=" + date +
                ", restaurant=" + restaurant +
                ", mainCourse=" + mainCourse +
                ", soup=" + soup +
                ", salad=" + salad +
                ", appetizer=" + appetizer +
                ", dessert=" + dessert +
                '}';
    }
}
