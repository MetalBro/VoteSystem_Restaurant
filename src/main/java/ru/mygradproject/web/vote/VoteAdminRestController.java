package ru.mygradproject.web.vote;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mygradproject.model.User;
import ru.mygradproject.model.Vote;
import ru.mygradproject.service.VoteService;
import ru.mygradproject.util.validation.DateBetweenException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteAdminRestController.REST_URL)
public class VoteAdminRestController {

    static final String REST_URL = "/rest/admin/votes/";

    private final VoteService voteService;

    public VoteAdminRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping(value = "/restaurants/{restaurantId}/between", produces = MediaType.APPLICATION_JSON_VALUE)     // +++
    public List<Vote> getBetween(@PathVariable("restaurantId") int restaurantId,
                                 @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        if (startDate == null || endDate == null) throw new DateBetweenException("You must input both dates");
        return voteService.findAllByDateBetweenAndRestaurantId(startDate, endDate, restaurantId);
    }

    @GetMapping(value = "/restaurants/{restaurantId}/usersBy", produces = MediaType.APPLICATION_JSON_VALUE)          // +++
    public List<User> getUsersToLunchThisDayAndRestaurant(@PathVariable("restaurantId") int restaurantId,
                                                          @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate){
        return voteService.findUsersForRestaurantAndDate(restaurantId, localDate);
    }

    @GetMapping(value = "/restaurants/{restaurantId}/by", produces = MediaType.APPLICATION_JSON_VALUE)          // +++
    public List<Vote> getByRestaurantAndDate(@PathVariable("restaurantId") int restaurantId,
                                             @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate){
        return voteService.findAllByRestaurantAndDate(restaurantId, localDate);
    }

}
