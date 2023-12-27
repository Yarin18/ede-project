package fact.it.repository;

import fact.it.dto.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface WorkoutRepository extends MongoRepository<Workout, String> {

}
