package ru.mygradproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mygradproject.AuthorizedUser;
import ru.mygradproject.repository.restaurant.RestaurantRepositoryImpl;
import ru.mygradproject.repository.user.UserRepository;
import ru.mygradproject.repository.user.UserRepositoryImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private RestaurantRepositoryImpl restaurantRepository;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
//        model.addAttribute("users", userService.getAll());
        model.addAttribute("users", userRepository.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:restaurans";
    }

    @GetMapping("/restaurants")
    public String meals(Model model) {
//        model.addAttribute("meals",
//                MealsUtil.getWithExceeded(mealService.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        model.addAttribute("restaurants", restaurantRepository.getAll());
        return "restaurants";
    }

}
