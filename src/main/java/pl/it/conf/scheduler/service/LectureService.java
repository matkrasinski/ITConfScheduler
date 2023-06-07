package pl.it.conf.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.it.conf.scheduler.dto.LectureDto;
import pl.it.conf.scheduler.dto.Mapper;
import pl.it.conf.scheduler.repository.ConferenceRepository;
import pl.it.conf.scheduler.repository.LectureRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final ConferenceRepository conferenceRepository;
    private final Mapper mapper;

    public List<LectureDto> browseLectures(Long conferenceId) {
        var conference = conferenceRepository.findById(conferenceId);
        if (conference.isEmpty())
            throw new IllegalArgumentException("Conference with given ID does not exist");

        return lectureRepository.findAllByConferenceId(conference.get())
                .stream()
                .map(mapper::mapLecture)
                .toList();
    }


}
