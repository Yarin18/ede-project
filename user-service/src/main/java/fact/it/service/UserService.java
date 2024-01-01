package fact.it.service;

import fact.it.dto.User;
import fact.it.dto.UserRequest;
import fact.it.dto.UserResponse;
import fact.it.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    private final WebClient webClient;

    /**
     * Creates a new user.
     * @param userRequest - the request we create the new user of.
     */
    public void createUser(final UserRequest userRequest) {
        final User user = User.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .workoutGoal(userRequest.getWorkoutGoal())
                .email(userRequest.getEmail())
                .build();
        userRepository.save(user);
    }

    /**
     * Updates a user.
     * @param id - the ID of the user we want to update.
     * @param userRequest - The newly updated user.
     */
    public void updateUser(final String id, final UserRequest userRequest) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            final User toUpdate = user.get();
            toUpdate.setEmail(userRequest.getEmail());
            toUpdate.setPassword(userRequest.getPassword());
            toUpdate.setName(userRequest.getName());
            toUpdate.setWorkoutGoal(userRequest.getWorkoutGoal());
        }
    }

    /**
     * Deletes a user.
     * @param id - the ID of the user we want to delete.
     */
    public void deleteUser(final String id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    /**
     * Returns a user based of the passed in ID.
     * @param id - the ID of the user we want to grab.
     * @return a UserResponse object.
     */
    public UserResponse getById(final String id) {
        return mapToUserResponse(userRepository.findById(id).orElse(null));
    }

    /**
     * Maps a User Object to a UserResponse object.
     * @param user - the user we're going to map.
     * @return a UserResponse object based of the user object we passed in.
     */
    private UserResponse mapToUserResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .workoutGoal(user.getWorkoutGoal())
                .password(user.getPassword())
                .build();
    }


}
