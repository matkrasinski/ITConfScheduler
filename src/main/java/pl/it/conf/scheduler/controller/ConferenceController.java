package pl.it.conf.scheduler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.it.conf.scheduler.model.Conference;
import pl.it.conf.scheduler.service.ConferenceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;

    @GetMapping
    @ResponseBody
    @Operation(summary = "Display all conferences", description = "Display all conferences")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All conferences retrieved",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"status\": 200, \"message\": \"Retrieve all conferences\"}"))),

            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"status\": 400, \"message\": \"Bad request\"}")))
    })
    public ResponseEntity<List<Conference>> displayAllConferences() {
        return ResponseEntity.ok(conferenceService.getConferences());
    }


}
