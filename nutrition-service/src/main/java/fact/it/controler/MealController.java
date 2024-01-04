package fact.it.controler;

import fact.it.dto.MealRequest;
import fact.it.dto.MealResponse;
import fact.it.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MealResponse> getAllMeals() {
        return mealService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMeal(final @PathVariable("id") Long id) {
        return mealService.deleteMeal(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MealResponse updateMeal(final @PathVariable("id") Long id, final @RequestBody MealRequest meal) {
        return mealService.updateMeal(id, meal);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<MealResponse> getMealsFromUser(final @RequestParam("userId") String userId) {
        return mealService.getAllMealsByUser(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public MealResponse createMeal(final @RequestBody MealRequest meal) {
        return mealService.createMeal(meal);
    }

    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MealResponse getById(final @PathVariable("id") Long id) {
        return mealService.findMealById(id);
    }

}
