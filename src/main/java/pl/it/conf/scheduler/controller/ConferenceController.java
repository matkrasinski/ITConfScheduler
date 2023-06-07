package pl.it.conf.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.it.conf.scheduler.model.Conference;
import pl.it.conf.scheduler.service.ConferenceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConferenceController {

    private final ConferenceService conferenceService;

    @GetMapping("/conferences")
    @ResponseBody
    public ResponseEntity<List<Conference>> displayAllConferences() {
        return ResponseEntity.ok(conferenceService.getConferences());
    }


}
