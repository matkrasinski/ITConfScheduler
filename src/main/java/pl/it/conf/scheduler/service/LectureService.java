package pl.it.conf.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.it.conf.scheduler.dto.LectureDto;
import pl.it.conf.scheduler.dto.LectureThemeDto;
import pl.it.conf.scheduler.dto.LectureWithAttendanceDto;
import pl.it.conf.scheduler.dto.Mapper;
import pl.it.conf.scheduler.exception.type.ForbiddenException;
import pl.it.conf.scheduler.model.Conference;
import pl.it.conf.scheduler.model.Lecture;
import pl.it.conf.scheduler.repository.ConferenceRepository;
import pl.it.conf.scheduler.repository.LectureRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    @Value("${organizer.key}")
    private String organizerKey;

    @Value("${organizer.maxSize}")
    private int maxSize;

    private final LectureRepository lectureRepository;
    private final ConferenceRepository conferenceRepository;
    private final Mapper mapper;

    public List<LectureDto> getLectures(Long conferenceId) {
        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        if (conference.isEmpty())
            throw new IllegalArgumentException("Conference with given ID does not exist");

        return lectureRepository.findAllByConferenceId(conference.get())
                .stream()
                .map(mapper::mapLecture)
                .toList();
    }

    public List<LectureWithAttendanceDto> getLecturesAttendance(String organizerKey, Long conferenceId) {
        Conference conference = validateConferenceAccess(organizerKey, conferenceId);

        List<Lecture> lectures = lectureRepository.findAllByConferenceId(conference);

        return lectures.stream().map(e -> mapper.mapLectureWithAttendance(e, maxSize)).toList();
    }

    public List<LectureThemeDto> getThemesInterest(String organizerKey, Long conferenceId) {
        List<Lecture> lectures = lectureRepository
                .findAllByConferenceId(validateConferenceAccess(organizerKey, conferenceId));

        Map<String, List<Lecture>> themeGroups = lectures.stream().collect(Collectors.groupingBy(Lecture::getTheme));

        List<LectureThemeDto> lectureThemeDtoList = new ArrayList<>();
        for (var entry : themeGroups.entrySet()) {
            double interest = 0;
            for (var i : entry.getValue())
                 interest += (double) (maxSize - i.getRemainingCapacity()) / maxSize;
            lectureThemeDtoList.add(LectureThemeDto.builder()
                            .theme(entry.getKey())
                            .interest((double) Math.round(interest * 10000) / 100)
                            .build());
        }

        return lectureThemeDtoList;
    }
    private Conference validateConferenceAccess(String organizerKey, Long conferenceId) {
        if (organizerKey.isEmpty() || !Objects.equals(organizerKey, this.organizerKey))
            throw new ForbiddenException("You are not an organizer of conference");

        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        if (conference.isEmpty())
            throw new IllegalArgumentException("Conference with given ID does not exist");
        return conference.get();
    }
}
