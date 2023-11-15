package com.TeamProject.TeamProject.Restaurant;

import com.TeamProject.TeamProject.Restaurant.Restaurant;
import com.TeamProject.TeamProject.Restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Restaurant> restaurantList = this.restaurantService.getRestaurant();
        model.addAttribute("restaurantList", restaurantList);
        return "Restaurant_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Restaurant restaurant = this.restaurantService.getRestaurantById(id);
        model.addAttribute("restaurant", restaurant);
        return "restaurant_detail";
    }
}
