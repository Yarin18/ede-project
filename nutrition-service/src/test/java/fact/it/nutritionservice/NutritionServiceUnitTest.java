package fact.it.nutritionservice;

import fact.it.dto.Meal;
import fact.it.dto.MealRequest;
import fact.it.dto.MealResponse;
import fact.it.repository.MealRepository;
import fact.it.service.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NutritionServiceUnitTest {

    @InjectMocks
    private MealService mealService;

    @Mock
    private MealRepository mealRepository;


    @Test
    public void testCreateMeal() {
        final MealRequest mealRequest = new MealRequest();
        mealRequest.setName("Breakfast");
        mealRequest.setDate(Date.from(Instant.now()));
        mealRequest.setTotalCalories(400);
        mealRequest.setUserId("user123");

        mealService.createMeal(mealRequest);

        verify(mealRepository, times(1)).save(any(Meal.class));
    }


    @Test
    public void testDeleteMeal() {
        final Meal meal = Meal.builder()
                .id(1L)
                .userId("user123")
                .name("Breakfast")
                .date(new Date())
                .totalCalories(400)
                .build();

        when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));

        mealService.deleteMeal(meal.getId());

        verify(mealRepository, times(1)).deleteById(meal.getId());
    }

    @Test
    public void testUpdateMeal() {
        final Meal meal = Meal.builder()
                .id(1L)
                .userId("user123")
                .name("Breakfast")
                .date(new Date())
                .totalCalories(400)
                .build();

        when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));

        final String updatedName = "Lunch";
        final int updatedCalories = 600;

        final MealRequest updatedMeal = MealRequest.builder()
                .userId(meal.getUserId())
                .name(updatedName)
                .date(meal.getDate())
                .totalCalories(updatedCalories)
                .build();

        final MealResponse updatedResponse = mealService.updateMeal(meal.getId(), updatedMeal);

        final Meal updated = new Meal(updatedResponse.getId(), updatedMeal.getDate(), updatedMeal.getName(), updatedMeal.getUserId(), updatedMeal.getTotalCalories());

        verify(mealRepository, times(1)).save(updated);
    }

    @Test
    public void testGetAllMeals() {
        final Date date = Date.from(Instant.now().plusSeconds(5000));
        Meal meal = new Meal();
        meal.setId(1L);
        meal.setName("Breakfast");
        meal.setUserId("user123");
        meal.setDate(date);
        meal.setTotalCalories(400);

        when(mealRepository.findAll()).thenReturn(List.of(meal));

        final List<MealResponse> meals = mealService.getAll();

        assertEquals(1, meals.size());
        assertEquals("Breakfast", meals.get(0).getName());
        assertEquals("user123", meals.get(0).getUserId());
        assertEquals(date, meals.get(0).getDate());
        assertEquals(400, meals.get(0).getTotalCalories());

        verify(mealRepository, times(1)).findAll();
    }


    @Test
    public void testGetAllMealsByUserId() {
        final Date d1 = new Date();
        final Date d2 = Date.from(Instant.now().plusMillis(10000));
        final Meal meal = new Meal();
        meal.setId(1L);
        meal.setName("Breakfast");
        meal.setUserId("user123");
        meal.setDate(d1);
        meal.setTotalCalories(400);

        final Meal meal2 = new Meal();
        meal2.setId(2L);
        meal2.setName("Lunch");
        meal2.setUserId("user456");
        meal2.setDate(new Date());
        meal2.setTotalCalories(600);

        final Meal meal3 = new Meal();
        meal3.setId(3L);
        meal3.setName("Dinner");
        meal3.setUserId("user123");
        meal3.setDate(new Date());
        meal3.setTotalCalories(800);

        final Meal meal4 = new Meal();
        meal4.setId(4L);
        meal4.setName("Snack");
        meal4.setUserId("user123");
        meal4.setDate(d2);
        meal4.setTotalCalories(200);

        when(mealRepository.findByUserId("user123")).thenReturn(List.of(meal, meal3, meal4));

        final List<MealResponse> meals = mealService.getAllMealsByUser("user123");

        assertEquals(3, meals.size());

        final MealResponse response1 = meals.get(0);
        assertEquals(1L, response1.getId());
        assertEquals("Breakfast", response1.getName());
        assertEquals("user123", response1.getUserId());
        assertEquals(d1, response1.getDate());
        assertEquals(400, response1.getTotalCalories());

        final MealResponse response2 = meals.get(1);
        assertEquals(3L, response2.getId());
        assertEquals("Dinner", response2.getName());
        assertEquals("user123", response2.getUserId());
        assertEquals(d1, response2.getDate());
        assertEquals(800, response2.getTotalCalories());

        final MealResponse response3 = meals.get(2);
        assertEquals(4L, response3.getId());
        assertEquals("Snack", response3.getName());
        assertEquals("user123", response3.getUserId());
        assertEquals(d2, response3.getDate());
        assertEquals(200, response3.getTotalCalories());

        verify(mealRepository, times(1)).findByUserId("user123");
    }
}
