package ru.mygradproject.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.mygradproject.model.User;

import java.util.List;
// DATA JPA
@Repository
public class UserRepositoryImpl {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private UserRepository userRepository;

//    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

//    @Override
    public boolean delete(int id) {
        return userRepository.delete(id) != 0;
    }

//    @Override
    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

//    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

//    @Override
    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }

//    @Override
//    public User getWithMeals(int id) {
//        return userRepository.getWithMeals(id);
//    }
}
