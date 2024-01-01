package fact.it.repository;

import fact.it.dto.Meal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByUserId(final String userId);

}
