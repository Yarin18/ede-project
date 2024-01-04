package fact.it.controller;

import fact.it.dto.UserRequest;
import fact.it.dto.UserResponse;
import fact.it.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAll() {
        return userService.getAllUsers();
    }

    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getById(final @PathVariable("id") String id) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse createUser(final @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(final @PathVariable("id") String id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(final @PathVariable("id") String id, final @RequestBody UserRequest userRequest) {
       return userService.updateUser(id, userRequest);
    }
}
