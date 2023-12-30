package fact.it.workoutplanservice;

import fact.it.dto.Workout;
import fact.it.dto.WorkoutRequest;
import fact.it.dto.WorkoutResponse;
import fact.it.repository.WorkoutRepository;
import fact.it.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkoutPlanServiceUnitTest {

    @InjectMocks
    private WorkoutService workoutService;

    @Mock
    private WorkoutRepository workoutRepository;


    @Test
    public void testCreateWorkout() {
        final WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setCardioWorkout(true);
        workoutRequest.setDate(Date.from(Instant.now()));
        workoutRequest.setName("Going for a run");
        workoutRequest.setMinutes(25);
        workoutRequest.setUserId("user123");

        workoutService.createWorkout(workoutRequest);

        verify(workoutRepository, times(1)).save(any(Workout.class));
    }

    @Test
    public void testGetAllWorkouts() {
        final Date date = Date.from(Instant.now().plusSeconds(5000));
        Workout workout = new Workout();
        workout.setId("1");
        workout.setName("Morning Jog");
        workout.setUserId("user123");
        workout.setDate(date);
        workout.setMinutes(30);
        workout.setCardioWorkout(true);

        when(workoutRepository.findAll()).thenReturn(List.of(workout));

        final List<WorkoutResponse> workouts = workoutService.getAllWorkouts();

        assertEquals(1, workouts.size());
        assertEquals("Morning Jog", workouts.get(0).getName());
        assertEquals("user123", workouts.get(0).getUserId());
        assertEquals(date, workouts.get(0).getDate());
        assertTrue(workouts.get(0).isCardioWorkout());
        assertEquals(30, workouts.get(0).getMinutes());

        verify(workoutRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllWorkoutByUserId() {
        final Date d1 = new Date();
        final Date d2 = Date.from(Instant.now().plus(50, ChronoUnit.HALF_DAYS));
        Workout workout = new Workout();
        workout.setId("1");
        workout.setName("Morning Run");
        workout.setUserId("user123");
        workout.setDate(d1);
        workout.setMinutes(30);
        workout.setCardioWorkout(true);

        Workout workout2 = new Workout();
        workout2.setId("2");
        workout2.setName("Weight Training");
        workout2.setUserId("user456");
        workout2.setDate(new Date());
        workout2.setMinutes(45);
        workout2.setCardioWorkout(false);

        Workout workout3 = new Workout();
        workout3.setId("3");
        workout3.setName("Cycling");
        workout3.setUserId("user789");
        workout3.setDate(new Date());
        workout3.setMinutes(60);
        workout3.setCardioWorkout(true);

        Workout workout4 = new Workout();
        workout4.setId("4");
        workout4.setName("Evening Weights");
        workout4.setUserId("user123");
        workout4.setDate(d2);
        workout4.setMinutes(40);
        workout4.setCardioWorkout(false);

        when(workoutRepository.findByUserId("user123")).thenReturn(List.of(workout, workout4));

        final List<WorkoutResponse> workouts = workoutService.getAllWorkoutsByUserId("user123");

        assertEquals(2, workouts.size());

        final WorkoutResponse response1 = workouts.get(0);
        assertEquals("1", response1.getId());
        assertEquals("Morning Run", response1.getName());
        assertEquals("user123", response1.getUserId());
        assertEquals(d1, response1.getDate());
        assertEquals(30, response1.getMinutes());
        assertTrue(response1.isCardioWorkout());

        final WorkoutResponse response2 = workouts.get(1);
        assertEquals("4", response2.getId());
        assertEquals("Evening Weights", response2.getName());
        assertEquals("user123", response2.getUserId());
        assertEquals(d2, response2.getDate());
        assertEquals(40, response2.getMinutes());
        assertFalse(response2.isCardioWorkout());

        verify(workoutRepository, times(1)).findByUserId("user123");
    }
}
