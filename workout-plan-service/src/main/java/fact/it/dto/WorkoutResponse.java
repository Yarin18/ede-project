package fact.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutResponse {

    private String id, name;
    private Date date;
    private int minutes;
    private boolean isCardioWorkout;

}
