package pl.it.conf.scheduler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.it.conf.scheduler.dto.UserDto;
import pl.it.conf.scheduler.payload.request.UserArg;
import pl.it.conf.scheduler.payload.response.SimpleResponse;
import pl.it.conf.scheduler.payload.response.UserResponse;
import pl.it.conf.scheduler.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseBody
    @Operation(summary = "Register new user", description = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered user",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"status\":201," +
                                    "\"message\":\"Successfully registered user\",\"user\":{\"login\":\"example\", " +
                                    "\"email\" : \"example@gmail.com\"}}"))),

            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                    value = "{\"status\": 400, \"message\": \"Bad request\"}"))),

            @ApiResponse(responseCode = "401", description = "Registration input validation error",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                    value = "{\"status\": 401, \"message\": \"Given login is taken\"," +
                            " \"error\": \"ValidationException\"}"))),
    })
    public ResponseEntity<UserResponse> register(@Parameter(description = "User login and email",
            schema = @Schema(implementation = UserArg.class)) @RequestBody @Valid UserArg userArg) {
        return new  ResponseEntity<>(
                userService.register(userArg),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    @ResponseBody
    @Operation(summary = "Update give user's email", description = "Update give user's email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User email successfully updated",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            value = "{\"status\": 201, \"message\": \"Successfully updated user email\"}"))),

            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"status\": 400, \"message\": \"Bad request\"}"))),

            @ApiResponse(responseCode = "404", description = "User with give login does not exist",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            value = "{\"status\": 404, \"message\": \"User with give login does not exist\"}")))
    })
    public ResponseEntity<SimpleResponse> update(@Parameter(description = "User login and email",
            schema = @Schema(implementation = UserArg.class)) @RequestBody @Valid UserArg userArg) {
        return ResponseEntity.accepted().body(userService.updateEmail(userArg));
    }

    @GetMapping("/all")
    @ResponseBody
    @Operation(summary = "Retrieve all users with their data", description = "Retrieve all users with their data")
    @Parameter(name = "organizerKey", description = "Conference organizer key", example = "secretKey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                                    value = "[{\"login\": \"example\",\"email\": \"example@gmail.com\"}]"))),

            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"status\": 400, \"message\": \"Bad request\"}"))),

            @ApiResponse(responseCode = "403", description = "You are not a organizer of the conference",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                                    value = "{\"status\": 403," +
                                            " \"message\": \"You are not a organizer of the conference\"}")))
    })
    public ResponseEntity<List<UserDto>> displayAllUsers(@RequestParam @Valid String organizerKey) {
        return ResponseEntity.ok(userService.displayAllUsers(organizerKey));
    }

}
