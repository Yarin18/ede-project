package fact.it.service;

import fact.it.dto.Meal;
import fact.it.dto.MealRequest;
import fact.it.dto.MealResponse;
import fact.it.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    /**
     * Returns a meal based of an ID.
     *
     * @param id - the ID we want to get the meal of.
     */
    public MealResponse findMealById(final Long id) {
        final Optional<Meal> res = mealRepository.findById(id);
        return mapToMealResponse(res.orElse(null));
    }

    /**
     * Returns all available meals.
     *
     * @return a List of meals.
     */
    public List<MealResponse> getAll() {
        return mealRepository.findAll().stream().map(this::mapToMealResponse).toList();
    }

    /**
     * Creates a new meal.
     *
     * @param mealRequest - the meal we want to create.
     */
    public void createMeal(final MealRequest mealRequest) {
        final Meal meal = Meal.builder()
                .userId(mealRequest.getUserId())
                .date(mealRequest.getDate())
                .name(mealRequest.getName())
                .totalCalories(mealRequest.getTotalCalories())
                .build();
        mealRepository.save(meal);
    }

    /**
     * Updates a meal.
     *
     * @param id          - the ID of the meal we want to update.
     * @param updatedMeal - the newly updated meal.
     */
    public Meal updateMeal(final Long id, final MealRequest updatedMeal) {
        final Optional<Meal> meal = mealRepository.findById(id);

        if (meal.isPresent()) {
            final Meal toUpdate = meal.get();
            toUpdate.setUserId(updatedMeal.getUserId());
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
     *
     * @param id - the ID of the meal we'd like to delete.
     */
    public void deleteMeal(final Long id) {
        final Optional<Meal> res = mealRepository.findById(id);
        res.ifPresent(meal -> mealRepository.deleteById(meal.getId()));
    }

    /**
     * Returns a list of all meals from the user;
     *
     * @param userId - the Id of the user we want to get the meals of.
     * @return a List of meal responses;
     */
    public List<MealResponse> getAllMealsByUser(final String userId) {
        return mealRepository.findByUserId(userId).stream().map(this::mapToMealResponse).collect(Collectors.toList());
    }

    /**
     * Returns a list of all meals from a user on a given date formatted as yyyy-MM-dd
     * @param date - The date we want to get the meals of.
     * @param userId - the User we want to get the meals of.
     * @return a List of MealResponse objects.
     */
    public List<MealResponse> getAllMealsOnDateFromUser(final String date, final String userId) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            final Date targetDate = dateFormat.parse(date);
            return mealRepository.findByUserId(userId).stream().filter(d -> isInTheSameDay(targetDate, d.getDate())).map(this::mapToMealResponse).toList();
        } catch (final Exception e) {
            return null;
        }
    }

    /**
     * Returns whether 2 dates are in the same day.
     * @param d1 - The first date to compare.
     * @param d2 - The second date to compare.
     * @return True if the dates are on the same day.
     */
    public static boolean isInTheSameDay(final Date d1, final Date d2) {
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Maps a Meal Object to a MealResponse object.
     *
     * @param meal - the meal we're going to map.
     * @return a MealResponse object based of the meal object we passed in.
     */
    private MealResponse mapToMealResponse(final Meal meal) {
        return MealResponse.builder()
                .id(meal.getId())
                .userId(meal.getUserId())
                .name(meal.getName())
                .date(meal.getDate())
                .totalCalories(meal.getTotalCalories())
                .build();
    }

}
