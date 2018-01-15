package ru.mygradproject.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mygradproject.model.User;
import ru.mygradproject.service.UserService;

import java.util.List;


public abstract class AbstractUserController {

    @Autowired
    private UserService userService;

    public List<User> getAll() {
//        log.info("getAll");
        return userService.getAll();
    }

    public User get(int id) {
//        log.info("get {}", id);
        return userService.get(id);
    }

    public User create(User user) {
//        log.info("create {}", user);
//        checkNew(user);
        return userService.create(user);
    }

    public void delete(int id) {
//        log.info("delete {}", id);
        userService.delete(id);
    }

    public void update(User user, int id) {
//        log.info("update {} with id={}", user, id);
//        assureIdConsistent(user, id);
        userService.update(user);
    }

    public User getByMail(String email) {
//        log.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
//        log.info((enabled ? "enable " : "disable ") + id);
        userService.enable(id, enabled);
    }
}