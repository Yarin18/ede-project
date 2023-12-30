package fact.it.service;

import fact.it.dto.Workout;
import fact.it.dto.WorkoutRequest;
import fact.it.dto.WorkoutResponse;
import fact.it.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .userId(workoutRequest.getUserId())
                .name(workoutRequest.getName())
                .date(workoutRequest.getDate())
                .minutes(workoutRequest.getMinutes())
                .isCardioWorkout(workoutRequest.isCardioWorkout())
                .build();
        workoutRepository.save(workout);
    }

    /**
     * @return - all workouts available.
     */
    public List<WorkoutResponse> getAllWorkouts() {
        return workoutRepository.findAll().stream().map(this::mapToWorkoutResponse).toList();
    }

    /**
     * Returns a list of workout responses based of the passed in userid.
     * @param userId - the UserId we want to get the workouts of.
     * @return a List<WorkoutResponse>.
     */
    public List<WorkoutResponse> getAllWorkoutsByUserId(final String userId) {
        return workoutRepository.findByUserId(userId).stream().map(this::mapToWorkoutResponse).toList();
    }

    /**
     * Edits a workout
     * @param id - The id of the workout we want to edit.
     * @param updatedWorkoutRequest - the request we're passing along to update.
     */
    public void updateWorkout(String id, WorkoutRequest updatedWorkoutRequest) {
        final Optional<Workout> existingWorkout = workoutRepository.findById(id);
        if (existingWorkout.isPresent()) {
            final Workout res = existingWorkout.get();
            res.setUserId(updatedWorkoutRequest.getUserId());
            res.setName(updatedWorkoutRequest.getName());
            res.setDate(updatedWorkoutRequest.getDate());
            res.setMinutes(updatedWorkoutRequest.getMinutes());
            res.setCardioWorkout(updatedWorkoutRequest.isCardioWorkout());
            workoutRepository.save(res);
        }
    }

    /**
     * @param id - Delete a workout with this id.
     */
    public void deleteWorkout(final String id) {
        final Optional<Workout> toDelete = workoutRepository.findById(id);
        toDelete.ifPresent(workoutRepository::delete);
    }

    /**
     * Maps a Workout Object to a WorkoutResponse object.
     * @param workout - the workout we are going to map from.
     * @return a WorkoutResponse object based of the workout object we passed in.
     */
    private WorkoutResponse mapToWorkoutResponse(final Workout workout) {
        return WorkoutResponse.builder()
                .id(workout.getId())
                .userId(workout.getUserId())
                .name(workout.getName())
                .date(workout.getDate())
                .minutes(workout.getMinutes())
                .isCardioWorkout(workout.isCardioWorkout())
                .build();
    }

}
