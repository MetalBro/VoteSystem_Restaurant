package ru.mygradproject.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mygradproject.model.User;
import ru.mygradproject.service.UserService;
import ru.mygradproject.util.validation.ValidationUtil;

import java.util.List;

import static ru.mygradproject.util.validation.ValidationUtil.checkNew;


public abstract class AbstractUserController {

    @Autowired
    private UserService userService;

    public List<User> getAll() {
        return userService.getAll();
    }

    public User get(int id) {
        return userService.get(id);
    }

    public User create(User user) {
        checkNew(user);
        return userService.create(user);
    }

    public void delete(int id) {
        userService.delete(id);
    }

    public void update(User user, int id) {
        ValidationUtil.assureIdConsistent(user, id);
        userService.update(user);
    }

    public User getByMail(String email) {
        return userService.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        userService.enable(id, enabled);
    }
}