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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createWorkout(final @RequestBody WorkoutRequest workoutRequest) {
        workoutService.createWorkout(workoutRequest);
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
    public void editWorkout(
            @PathVariable("id") final String id,
            @RequestBody final WorkoutRequest updatedWorkoutRequest) {
        workoutService.updateWorkout(id, updatedWorkoutRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkout(@PathVariable("id") final String id) {
        workoutService.deleteWorkout(id);
    }

}
