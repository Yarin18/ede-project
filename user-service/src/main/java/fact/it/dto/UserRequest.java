package fact.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest implements Nameable {

    private String name, password, email;
    // goal of hitting an x amount of workouts per week
    private int workoutGoal;

    @Override
    public String getUserName() {
        return this.name;
    }
}
