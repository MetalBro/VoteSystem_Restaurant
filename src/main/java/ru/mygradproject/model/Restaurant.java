package ru.mygradproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity{

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 5, max = 200)
    private String address;

    @Column(name = "cookery", nullable = false)
    @NotBlank
    @Size(min = 5, max = 200)
    private String cookery;

    public Restaurant(){

    }

    public Restaurant(Restaurant restaurant){
        this(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getCookery());
    }

    public Restaurant(Integer id, String name, @NotBlank @Size(min = 5, max = 200) String address, @NotBlank @Size(min = 5, max = 200) String cookery) {
        super(id, name);
        this.address = address;
        this.cookery = cookery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCookery() {
        return cookery;
    }

    public void setCookery(String cookery) {
        this.cookery = cookery;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", id=" + id +
                ", name=" + name +
                ", address=" + address +
                "cookery=" + cookery +
                '}';
    }
}
