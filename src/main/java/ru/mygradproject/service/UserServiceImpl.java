package ru.mygradproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.mygradproject.model.User;
import ru.mygradproject.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return userRepository.getByEmail(email);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }
}
