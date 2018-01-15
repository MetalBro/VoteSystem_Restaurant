package ru.mygradproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mygradproject.AuthorizedUser;
import ru.mygradproject.service.RestaurantService;
import ru.mygradproject.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    private UserService userService;

    private RestaurantService restaurantService;

    @Autowired
    public RootController(UserService userService, RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

//    @PostMapping("/users")
//    public String setUser(HttpServletRequest request) {
//        int userId = Integer.valueOf(request.getParameter("userId"));
//        AuthorizedUser.setId(userId);
//        return "redirect:restaurants";
//    }

    @GetMapping("/restaurants")
    public String meals(Model model) {
        model.addAttribute("restaurants", restaurantService.getAll());
        return "restaurants";
    }

}