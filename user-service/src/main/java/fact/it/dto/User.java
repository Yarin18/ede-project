package fact.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id, name, password, email;
    // goal of hitting an x amount of workouts per week
    private int workoutGoal;
}
