package fact.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Nameable {

    private String id, name, password, email;
    // goal of hitting an x amount of workouts per week
    private int workoutGoal;

    @Override
    public String getUserName() {
        return this.name;
    }
}
