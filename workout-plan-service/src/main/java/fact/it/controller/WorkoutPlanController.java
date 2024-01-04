package fact.it.controller;

import fact.it.dto.WorkoutRequest;
import fact.it.dto.WorkoutResponse;
import fact.it.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutService workoutService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        return "Test success!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public WorkoutResponse createWorkout(final @RequestBody WorkoutRequest workoutRequest) {
        return workoutService.createWorkout(workoutRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkoutResponse getWorkoutById(final @PathVariable("id") String id) {
        return workoutService.getWorkoutById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkoutResponse> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkoutResponse editWorkout(
            @PathVariable("id") final String id,
            @RequestBody final WorkoutRequest updatedWorkoutRequest) {
        return workoutService.updateWorkout(id, updatedWorkoutRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteWorkout(@PathVariable("id") final String id) {
        return workoutService.deleteWorkout(id);
    }

}
