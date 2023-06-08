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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.it.conf.scheduler.dto.ReservationDto;
import pl.it.conf.scheduler.payload.request.UserArg;
import pl.it.conf.scheduler.payload.response.SimpleResponse;
import pl.it.conf.scheduler.service.ReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    @ResponseBody
    @Operation(summary = "Make reservation for the given lecture", description = "Make reservation for the given lecture")
    @Parameter(name = "lectureId", description = "Lecture ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description  = "You made reservation for given user",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":202," +
                                    "\"message\":\"Reservation made successfully\"}"))),

            @ApiResponse(responseCode = "400", description  = "Bad request",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":400," +
                                    "\"message\":\"Bad request\"}"))),

            @ApiResponse(responseCode = "403",
                    description  = "You are not allowed to make a reservation for the given lecture",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":403," +
                                    "\"message\":\"You are not allowed to make a reservation for the given lecture\"}"))),

            @ApiResponse(responseCode = "404", description  = "Resource does not exist",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404," +
                                    "\"message\":\"Resource does not exist\"}")))
    })
    public ResponseEntity<SimpleResponse> makeReservation(@RequestParam @Valid Long lectureId,
                                                          @Parameter(name = "userArg",
                                                                  description = "User login and email",
                                                                  schema = @Schema(implementation = UserArg.class))
                                                          @RequestBody @Valid UserArg userArg) {
        return ResponseEntity.accepted().body(reservationService.makeReservation(lectureId, userArg));
    }

    @GetMapping("/list")
    @ResponseBody
    @Operation(summary = "List all reservations for the given user", description = "List all reservations for the given user")
    @Parameter(name = "login", description = "User login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description  = "All reservations for the given user successfully retrieved",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "[{\"reservationId\":10," +
                                   "\"lectureId\":\"6\", \"startTime\": \"14:00:00\", \"endTime\": \"15:45:00\"," +
                                    "\"theme\": \"Fuzzy logic\"}]"))),

            @ApiResponse(responseCode = "400", description  = "Bad request",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":400," +
                                    "\"message\":\"Bad request\"}"))),

            @ApiResponse(responseCode = "404", description  = "Resource does not exist",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404," +
                                    "\"message\":\"Resource does not exist\"}")))
    })
    public ResponseEntity<List<ReservationDto>> displayReservations(@RequestParam @Valid String login) {
        return ResponseEntity.ok(reservationService.listReservations(login));
    }

    @DeleteMapping("/cancel")
    @ResponseBody
    @Operation(summary = "Cancel reservation for the given user", description = "Cancel reservation for the given user")
    @Parameter(name = "reservationId", description = "Reservation ID")
    @Parameter(name = "login", description = "User login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Reservations cancelled",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"status\":202," +
                            "\"message\":\"Successfully canceled reservation\"}"))),

            @ApiResponse(responseCode = "403", description = "You cannot cancel this reservation",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":403," +
                                    "\"message\":\"You cannot cancel this reservation\"}"))),

            @ApiResponse(responseCode = "404", description  = "Resource does not exist",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404," +
                                    "\"message\":\"Resource does not exist\"}")))
    })
    public ResponseEntity<SimpleResponse> cancelReservation(@RequestParam @Valid Long reservationId,
                                                            @RequestParam @Valid String login) {
        return ResponseEntity.accepted().body(reservationService.cancelReservation(login, reservationId));
    }

}
