package fact.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class WorkoutRequest implements Nameable {

    private String name, userId, id;
    private Date date;
    private int minutes;
    private boolean isCardioWorkout;

    @Override
    public String getUserName() {
        return this.name;
    }
}
