package fact.it.service;

import fact.it.dto.UserRequest;
import fact.it.dto.UserResponse;
import fact.it.model.User;
import fact.it.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param userRequest - the request we create the new user of.
     */
    public UserResponse createUser(final UserRequest userRequest) {
        final User user = User.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .workoutGoal(userRequest.getWorkoutGoal())
                .email(userRequest.getEmail())
                .build();
        userRepository.save(user);
        return mapToUserResponse(user);
    }

    /**
     * Returns a User based of an ID.
     *
     * @param id - The ID of the user.
     * @return a User Response object.
     */
    public UserResponse getUserById(final String id) {
        return mapToUserResponse(userRepository.findById(id).orElse(null));
    }

    /**
     * Updates a user.
     *
     * @param id          - the ID of the user we want to update.
     * @param userRequest - The newly updated user.
     */
    public UserResponse updateUser(final String id, final UserRequest userRequest) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            final User toUpdate = user.get();
            toUpdate.setEmail(userRequest.getEmail());
            toUpdate.setPassword(userRequest.getPassword());
            toUpdate.setName(userRequest.getName());
            toUpdate.setWorkoutGoal(userRequest.getWorkoutGoal());
            userRepository.save(toUpdate);
            return mapToUserResponse(toUpdate);
        } else return null;
    }

    /**
     * Deletes a user.
     *
     * @param id - the ID of the user we want to delete.
     */
    public String deleteUser(final String id) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return "Success";
        } else return "Failed";
    }

    /**
     * Returns a user based of the passed in ID.
     *
     * @param id - the ID of the user we want to grab.
     * @return a UserResponse object.
     */
    public UserResponse getById(final String id) {
        return mapToUserResponse(userRepository.findById(id).orElse(null));
    }

    /**
     * Returns a list of all users.
     *
     * @return - a List of all users mapped to UserResponse objects.
     */
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToUserResponse).toList();
    }


    /**
     * Maps a User Object to a UserResponse object.
     *
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
