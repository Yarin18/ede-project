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

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkoutResponse> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }
}
