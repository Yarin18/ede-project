package fact.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String name, password, email;
    // goal of hitting an x amount of workouts per week
    private int workoutGoal;

}
