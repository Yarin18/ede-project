import fact.it.dto.UserRequest;
import fact.it.dto.UserResponse;
import fact.it.dto.WorkoutRequest;
import fact.it.dto.WorkoutResponse;
import fact.it.model.User;
import fact.it.repository.UserRepository;
import fact.it.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceApplicationUnitTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

//    @Mock
//    private WebClient webClient;
//
//    @Mock
//    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
//
//    @Mock
//    private WebClient.RequestHeadersSpec requestHeadersSpec;
//
//    @Mock
//    private WebClient.ResponseSpec responseSpec;
//
//    @BeforeEach
//    void setUp() {
//        ReflectionTestUtils.setField(userService, "workout-plan-service", "http://localhost:8081");
//        ReflectionTestUtils.setField(userService, "nutrition-service", "http://localhost:8082");
//    }

    @Test
    public void testCreateUser() {
        final UserRequest userRequest = UserRequest.builder()
                .name("John Doe")
                .password("password")
                .email("john@example.com")
                .workoutGoal(5)
                .build();

        final User savedUser = User.builder()
                .id("1")
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .workoutGoal(userRequest.getWorkoutGoal())
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        final UserResponse userResponse = userService.createUser(userRequest);

        assertNotNull(userResponse);
        assertEquals("John Doe", userResponse.getName());
        assertEquals("password", userResponse.getPassword());
        assertEquals("john@example.com", userResponse.getEmail());
        assertEquals(5, userResponse.getWorkoutGoal());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        final User user = User.builder()
                .id("1")
                .workoutGoal(8)
                .password("pw")
                .email("email@email.com")
                .name("Jaff").build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUser(user.getId());

        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    public void testUpdateUser() {
        final String userId = "1";
        final UserRequest updatedUser = UserRequest.builder()
                .name("John")
                .password("newPass")
                .workoutGoal(8)
                .build();

        final User existingUser = User.builder()
                .id(userId)
                .name("John Doe")
                .password("password")
                .email("john@example.com")
                .workoutGoal(5)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        final UserResponse updatedResponse = userService.updateUser(userId, updatedUser);

        assertNotNull(updatedResponse);
        assertEquals(userId, updatedResponse.getId());
        assertEquals("John", updatedResponse.getName());
        assertEquals("newPass", updatedResponse.getPassword());
        assertEquals(8, updatedResponse.getWorkoutGoal());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetAllUsers() {
        final User user1 = User.builder()
                .id("1")
                .name("John Doe")
                .password("password")
                .email("john@example.com")
                .workoutGoal(5)
                .build();

        final User user2 = User.builder()
                .id("2")
                .name("Jane Smith")
                .password("password123")
                .email("jane@example.com")
                .workoutGoal(7)
                .build();

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        final List<UserResponse> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());

        final UserResponse userResponse1 = users.get(0);
        assertEquals("1", userResponse1.getId());
        assertEquals("John Doe", userResponse1.getName());
        assertEquals("password", userResponse1.getPassword());
        assertEquals("john@example.com", userResponse1.getEmail());
        assertEquals(5, userResponse1.getWorkoutGoal());

        final UserResponse userResponse2 = users.get(1);
        assertEquals("2", userResponse2.getId());
        assertEquals("Jane Smith", userResponse2.getName());
        assertEquals("password123", userResponse2.getPassword());
        assertEquals("jane@example.com", userResponse2.getEmail());
        assertEquals(7, userResponse2.getWorkoutGoal());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        String userId = "1";

        final User user = User.builder()
                .id(userId)
                .name("John Doe")
                .password("password")
                .email("john@example.com")
                .workoutGoal(5)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        final UserResponse userResponse = userService.getUserById(userId);

        assertNotNull(userResponse);
        assertEquals(userId, userResponse.getId());
        assertEquals("John Doe", userResponse.getName());
        assertEquals("password", userResponse.getPassword());
        assertEquals("john@example.com", userResponse.getEmail());
        assertEquals(5, userResponse.getWorkoutGoal());

        verify(userRepository, times(1)).findById(userId);
    }

//    @Test
//    public void testCreateWorkout_Success() {
//        // Dummy data
//        WorkoutRequest workoutRequest = WorkoutRequest.builder()
//                .userId("user123")
//                .name("Morning Run")
//                .minutes(30)
//                .isCardioWorkout(true)
//                .date(new Date())
//                .build();
//
//        WorkoutResponse workoutResponse = WorkoutResponse.builder()
//                .id("1")
//                .userId(workoutRequest.getUserId())
//                .name(workoutRequest.getName())
//                .minutes(workoutRequest.getMinutes())
//                .isCardioWorkout(workoutRequest.isCardioWorkout())
//                .date(workoutRequest.getDate())
//                .build();
//
//        when(webClient.post()
//                .uri(eq("http://workout-plan-service:8081/api/workout"))
//                .header(eq(HttpHeaders.CONTENT_TYPE), eq(MediaType.APPLICATION_JSON_VALUE))
//                .body(any(Mono.class), eq(WorkoutResponse.class))
//                .retrieve()
//                .bodyToMono(eq(WorkoutResponse.class))
//        ).thenReturn(Mono.just(workoutResponse));
//
//        Mono<String> result = userService.createWorkout(workoutRequest);
//
//        // Dummy assert
//        assertEquals("Success", result.block());
//
//        verify(webClient, times(1)).post()
//                .uri(eq("http://workout-plan-service:8081/api/workout"))
//                .header(eq(HttpHeaders.CONTENT_TYPE), eq(MediaType.APPLICATION_JSON_VALUE))
//                .body(any(Mono.class), eq(WorkoutResponse.class))
//                .retrieve()
//                .bodyToMono(eq(WorkoutResponse.class));
//    }
//
//    @Test
//    public void testCreateWorkout_Failure() {
//        // Dummy data
//        WorkoutRequest workoutRequest = WorkoutRequest.builder()
//                .userId("user123")
//                .name("Morning Run")
//                .minutes(30)
//                .isCardioWorkout(true)
//                .date(new Date())
//                .build();
//
//        when(webClient.post()
//                .uri(eq("http://workout-plan-service:8081/api/workout"))
//                .header(eq(HttpHeaders.CONTENT_TYPE), eq(MediaType.APPLICATION_JSON_VALUE))
//                .body(any(Mono.class), eq(WorkoutResponse.class))
//                .retrieve()
//                .bodyToMono(eq(WorkoutResponse.class))
//        ).thenReturn(Mono.error(new RuntimeException("Something went wrong")));
//
//        Mono<String> result = userService.createWorkout(workoutRequest);
//
//        // Dummy assert
//        assertEquals("Failed", result.block());
//
//        verify(webClient, times(1)).post()
//                .uri(eq("http://workout-plan-service:8081/api/workout"))
//                .header(eq(HttpHeaders.CONTENT_TYPE), eq(MediaType.APPLICATION_JSON_VALUE))
//                .body(any(Mono.class), eq(WorkoutResponse.class))
//                .retrieve()
//                .bodyToMono(eq(WorkoutResponse.class));
//    }


}

