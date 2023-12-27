package fact.it.repository;

import fact.it.dto.Meal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MealRepository extends JpaRepository<Meal, Long> {



}