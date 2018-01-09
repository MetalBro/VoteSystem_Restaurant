package ru.mygradproject.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class VotePK implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

//    @Column(name = "user_id", nullable = false)
//    @NotNull
//    private Integer user_id;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    public VotePK() {
    }

    public VotePK(@NotNull User user, @NotNull LocalDate date) {
        this.user = user;
        this.date = date;
    }

//    public VotePK(@NotNull Integer user_id, @NotNull LocalDate date) {
//        this.user_id = user_id;
//        this.date = date;
//    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    public Integer getUser_id() {
//        return user_id;
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
