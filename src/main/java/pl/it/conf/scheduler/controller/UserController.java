package pl.it.conf.scheduler.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.it.conf.scheduler.dto.UserDto;
import pl.it.conf.scheduler.payload.request.UserArg;
import pl.it.conf.scheduler.payload.response.UserResponse;
import pl.it.conf.scheduler.payload.response.SimpleResponse;
import pl.it.conf.scheduler.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/register")
    @ResponseBody
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserArg request) {
        return new ResponseEntity<>(
                userService.register(request),
                HttpStatus.CREATED);
    }

    @PutMapping("/users/update")
    @ResponseBody
    public ResponseEntity<SimpleResponse> update(@RequestBody @Valid UserArg request) {
        return new ResponseEntity<>(
                userService.updateEmail(request),
                HttpStatus.OK
        );
    }

    @GetMapping("/users/all")
    @ResponseBody
    public ResponseEntity<List<UserDto>> displayAllUsers(@RequestParam @Valid String organizerKey) {
        return new ResponseEntity<>(
                userService.displayAllUsers(organizerKey),
                HttpStatus.OK
        );
    }

}
