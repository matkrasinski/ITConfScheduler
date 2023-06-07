package pl.it.conf.scheduler.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.it.conf.scheduler.dto.LectureDto;
import pl.it.conf.scheduler.dto.LectureWithAttendanceDto;
import pl.it.conf.scheduler.service.LectureService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<LectureDto>> browseConferenceLectures(@RequestParam @Valid Long conferenceId) {
        return ResponseEntity.ok(lectureService.getLectures(conferenceId));
    }

    @GetMapping("/attendance")
    @ResponseBody
    public ResponseEntity<List<LectureWithAttendanceDto>> browseLecturesAttendance(@RequestParam @Valid String organizerKey,
                                                                                   @RequestParam @Valid Long conferenceId) {
        return ResponseEntity.ok(lectureService.getLecturesAttendance(organizerKey, conferenceId));
    }

    @GetMapping("/themes")
    @ResponseBody
    public ResponseEntity<List<?>> browseThemesInterest(@RequestParam @Valid String organizerKey,
                                                        @RequestParam @Valid Long conferenceId) {
        return ResponseEntity.ok(lectureService.getThemesInterest(organizerKey, conferenceId));
    }

}
