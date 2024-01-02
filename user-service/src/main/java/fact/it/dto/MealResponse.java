package fact.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealResponse {
    private Long id;
    private Date date;
    private String name, userId;
    private int totalCalories;
}
