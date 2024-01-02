package fact.it.controller;

import fact.it.dto.*;
import fact.it.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAll() {
        return userService.getAllUsers();
    }

    @RequestMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getById(final @PathVariable("id") String id) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(final @RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(final @PathVariable("id") String id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(final @PathVariable("id") String id, final @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }



    @GetMapping("workouts/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkoutResponse> getWorkoutsFromUser(final @PathVariable("userId") String id) {
        return userService.getWorkoutsFromUser(id).collectList().block();
    }

    @GetMapping("meals/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MealResponse> getMealsFromUser(final @PathVariable("userId") String id) {
        return userService.getMealsFromUser(id).collectList().block();
    }

    @PostMapping("/workouts")
    @ResponseStatus(HttpStatus.OK)
    public void createWorkoutForUser(final @RequestBody WorkoutRequest workoutRequest) {
        userService.createWorkout(workoutRequest);
    }
    @PostMapping("/meals")
    @ResponseStatus(HttpStatus.OK)
    public void createWorkoutForUser(final @RequestBody MealRequest mealRequest) {
        userService.createMeal(mealRequest);
    }

}
