package pl.it.conf.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.it.conf.scheduler.dto.LectureDto;
import pl.it.conf.scheduler.service.LectureService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    @GetMapping("/lectures")
    @ResponseBody
    public ResponseEntity<List<LectureDto>> browseConferenceLectures(@RequestParam Long conferenceId) {
        return ResponseEntity.ok(lectureService.browseLectures(conferenceId));
    }

}
