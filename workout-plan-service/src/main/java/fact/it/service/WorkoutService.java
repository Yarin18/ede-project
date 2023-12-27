package fact.it.service;

import fact.it.dto.Workout;
import fact.it.dto.WorkoutRequest;
import fact.it.dto.WorkoutResponse;
import fact.it.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    /**
     * Creates a new workout.
     * @param workoutRequest - the request we base our newly created workout of.
     */
    public void createWorkout(final WorkoutRequest workoutRequest) {
        final Workout workout = Workout.builder()
                .name(workoutRequest.getName())
                .date(workoutRequest.getDate())
                .minutes(workoutRequest.getMinutes())
                .isCardioWorkout(workoutRequest.isCardioWorkout())
                .build();
        workoutRepository.save(workout);
    }

    public List<WorkoutResponse> getAllWorkouts() {
        return workoutRepository.findAll().stream().map(this::mapToWorkoutResponse).toList();
    }

    /**
     * Maps a Workout Object to a WorkoutResponse object.
     * @param workout - the workout we're going to map.
     * @return a WorkoutResponse object based of the workout object we passed in.
     */
    private WorkoutResponse mapToWorkoutResponse(final Workout workout) {
        return WorkoutResponse.builder()
                .id(workout.getId())
                .name(workout.getName())
                .date(workout.getDate())
                .minutes(workout.getMinutes())
                .isCardioWorkout(workout.isCardioWorkout())
                .build();
    }

}
