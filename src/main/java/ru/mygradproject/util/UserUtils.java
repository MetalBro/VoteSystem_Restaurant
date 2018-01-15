package ru.mygradproject.util;

import ru.mygradproject.model.User;
import ru.mygradproject.to.UserTo;

public class UserUtils {

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
