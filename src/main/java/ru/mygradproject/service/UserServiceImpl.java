package ru.mygradproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.mygradproject.model.User;
import ru.mygradproject.repository.UserRepository;

import java.util.List;

//@Service("userService")
@Service
public class UserServiceImpl implements UserService{
//public class UserServiceImpl implements UserService, UserDetailsService{

    private final UserRepository userRepository;

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
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

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(user);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.getByEmail(email.toLowerCase());
//        if (user == null) {
//            throw new UsernameNotFoundException("User " + email + " is not found");
//        }
//        return new AuthorizedUser(user);
//    }
}
