package fact.it.nutritionservice;

import fact.it.repository.MealRepository;
import fact.it.service.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NutritionServiceUnitTest {

    @InjectMocks
    private MealService mealService;

    @Mock
    private MealRepository mealRepository;


}
