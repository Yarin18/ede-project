package fact.it.service;

import fact.it.dto.Meal;
import fact.it.dto.MealRequest;
import fact.it.dto.MealResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fact.it.repository.MealRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    /**
     * Returns a meal based of an ID.
     * @param id - the ID we want to get the meal of.
     */
    public Meal findMealById(final Long id) {
        final Optional<Meal> res = mealRepository.findById(id);
        return res.orElse(null);
    }

    /**
     * Returns all available meals.
     * @return a List of meals.
     */
    public List<MealResponse> getAll() {
        return mealRepository.findAll().stream().map(this::mapToMealResponse).toList();
    }

    /**
     * Creates a new meal.
     * @param mealRequest - the meal we want to create.
     */
    public Meal createMeal(final MealRequest mealRequest) {
        final Meal meal = Meal.builder()
                .date(mealRequest.getDate())
                .name(mealRequest.getName())
                .totalCalories(mealRequest.getTotalCalories())
                .build();
        mealRepository.save(meal);
        return meal;
    }

    /**
     * Updates a meal.
     * @param id - the ID of the meal we want to update.
     * @param updatedMeal - the newly updated meal.
     */
    public Meal updateMeal(final Long id, final MealRequest updatedMeal) {
        final Optional<Meal> meal = mealRepository.findById(id);

        if (meal.isPresent()) {
            Meal toUpdate = meal.get();
            toUpdate.setDate(updatedMeal.getDate());
            toUpdate.setName(updatedMeal.getName());
            toUpdate.setTotalCalories(updatedMeal.getTotalCalories());

            mealRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    /**
     * Deletes a meal.
     * @param id - the ID of the meal we'd like to delete.
     */
    public Meal deleteMeal(final Long id) {
        final Optional<Meal> res = mealRepository.findById(id);
        res.ifPresent(mealRepository::delete);
        return res.orElse(null);
    }

    /**
     * Maps a Meal Object to a MealResponse object.
     * @param meal - the meal we're going to map.
     * @return a MealResponse object based of the meal object we passed in.
     */
    private MealResponse mapToMealResponse(final Meal meal) {
            return MealResponse.builder()
                    .id(meal.getId())
                    .name(meal.getName())
                    .date(meal.getDate())
                    .totalCalories(meal.getTotalCalories())
                    .build();
    }

}