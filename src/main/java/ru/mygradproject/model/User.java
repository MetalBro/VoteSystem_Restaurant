package ru.mygradproject.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity{

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    @Enumerated(EnumType.STRING)
//    @BatchSize(size = 200)
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private String role;

    public User(){

    }

    public User (User u){
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistered(), u.isEnabled(), u.getRole());
    }

    public User(Integer id, String name, String email, String password, String role) {
        this(id, name, email, password, new Date(), true, role);
    }

    public User(Integer id, String name, @Email @NotBlank @Size(max = 100) String email, @NotBlank @Size(min = 5, max = 64) String password, @NotNull Date registered, Boolean enabled, String role) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //    public Set<Role> getRoles() {
//        return roles;
//    }

//    public void setRoles(Collection<Role> roles) {
//        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", password=" + password +
                ", registered=" + registered +
                ", role=" + role +
                '}';
    }
}
