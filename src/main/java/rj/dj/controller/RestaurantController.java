package rj.dj.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rj.dj.dto.FoodItem;
import rj.dj.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	private final RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	@GetMapping("/menu")
	public ResponseEntity<?> getMenu() {
		JSONObject res = new JSONObject();
		List<FoodItem> availableFood = restaurantService.getAvailableFood();
		if (!availableFood.isEmpty()) {
			res.put("status", 200);
			res.put("result", availableFood);
			res.put("message", "data return sucessfully.");
		} else {
			res.put("status", 404);
			res.put("result", "");
			res.put("message",
					"the restaurant cannot accept orders right now because its online ordering schedule does not match its current operating status, or the system has automatically paused the store");
		}
		return ResponseEntity.ok(res.toString().toString());
	}
}
