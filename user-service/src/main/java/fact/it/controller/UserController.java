package fact.it.controller;

import fact.it.dto.UserRequest;
import fact.it.dto.UserResponse;
import fact.it.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

//    @Autowired
//    private MessageSender messageSender;

    private final UserService userService;

    @RequestMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getById(final @PathVariable("id") String id) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(final @RequestBody UserRequest userRequest) {
//        messageSender.sendMessage(userRequest, UserTopic.CREATE);
        userService.createUser(userRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(final @PathVariable("id") String id) {
//        messageSender.sendMessage(userService.getById(id), UserTopic.DELETE);
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(final @PathVariable("id") String id, final @RequestBody UserRequest userRequest) {
//        messageSender.sendMessage(userRequest, UserTopic.UPDATE);
        userService.updateUser(id, userRequest);
    }

    public enum UserTopic {

        CREATE("create-user"),
        DELETE("delete-user"),
        UPDATE("update-user");

        private final String name;

        UserTopic(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
