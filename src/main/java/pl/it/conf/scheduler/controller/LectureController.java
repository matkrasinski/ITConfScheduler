package pl.it.conf.scheduler.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.it.conf.scheduler.dto.LectureDto;
import pl.it.conf.scheduler.dto.LectureThemeDto;
import pl.it.conf.scheduler.dto.LectureWithAttendanceDto;
import pl.it.conf.scheduler.service.LectureService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;

    @ResponseBody
    @Operation(summary = "Browse lectures for the given conference", description = "Browse lectures for the given conference")
    @Parameter(name = "conferenceId", description = "Conference ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectures retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "[{\"lectureId\": 1," +
                                    "\"theme\": \"Software engineering\"," +
                                    "\"startTime\": \"10:00:00\"," +
                                    "\"endTime\": \"11:45:00\"," +
                                    "\"remainingCapacity\": 4\n}]"))),

            @ApiResponse(responseCode = "400", description  = "Bad request",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":400," +
                                    "\"message\":\"Bad request\"}"))),

            @ApiResponse(responseCode = "404", description  = "Conference with given ID does not exist",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404," +
                                    "\"message\":\"Conference with given ID does not exist\"}")))
    })
    @GetMapping
    public ResponseEntity<List<LectureDto>> browseConferenceLectures(@RequestParam @Valid Long conferenceId) {
        return ResponseEntity.ok(lectureService.getLectures(conferenceId));
    }

    @GetMapping("/attendance")
    @ResponseBody
    @Operation(summary = "Browse attendance for all conference lectures", description = "Browse attendance for all conference lectures")
    @Parameters(value = {
            @Parameter(name = "organizerKey", description = "Conference organizer key"),
            @Parameter(name = "conferenceId", description = "Conference ID")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectures with attendance retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "[{\"lectureId\": 1," +
                                    "\"theme\": \"Software engineering\"," +
                                    "\"startTime\": \"10:00:00\"," +
                                    "\"endTime\": \"11:45:00\"," +
                                    "\"attendance\": 20.0\n}]"))),

            @ApiResponse(responseCode = "400", description  = "Bad request",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":400," +
                                    "\"message\":\"Bad request\"}"))),

            @ApiResponse(responseCode = "403", description = "You are not an organizer of the conference",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":403," +
                                    "\"message\":\"You are not an organizer of conference\"}"))),

            @ApiResponse(responseCode = "404", description  = "Conference with given ID does not exist",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404," +
                                    "\"message\":\"Conference with given ID does not exist\"}")))
    })
    public ResponseEntity<List<LectureWithAttendanceDto>> browseLecturesAttendance(@RequestParam @Valid String organizerKey,
                                                                                   @RequestParam @Valid Long conferenceId) {
        return ResponseEntity.ok(lectureService.getLecturesAttendance(organizerKey, conferenceId));
    }

    @GetMapping("/themes")
    @ResponseBody
    @Operation(summary = "Browse themes interests based on lectures attendance", description = "Browse themes interests based on lectures attendance")
    @Parameters(value = {
            @Parameter(name = "organizerKey", description = "Conference organizer key"),
            @Parameter(name = "conferenceId", description = "Conference ID")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Themes interests retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "[{\"theme\": \"Software engineering\"," +
                                    "\"interest\": 20.0\n}]"))),

            @ApiResponse(responseCode = "400", description  = "Bad request",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":400," +
                                    "\"message\":\"Bad request\"}"))),

            @ApiResponse(responseCode = "403", description = "You are not an organizer of the conference",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":403," +
                                    "\"message\":\"You are not an organizer of conference\"}"))),

            @ApiResponse(responseCode = "404", description  = "Conference with given ID does not exist",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\":404," +
                                    "\"message\":\"Conference with given ID does not exist\"}")))
    })
    public ResponseEntity<List<LectureThemeDto>> browseThemesInterest(@RequestParam @Valid String organizerKey,
                                                                      @RequestParam @Valid Long conferenceId) {
        return ResponseEntity.ok(lectureService.getThemesInterest(organizerKey, conferenceId));
    }

}
