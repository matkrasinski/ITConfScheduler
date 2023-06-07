package pl.it.conf.scheduler.dto;

import org.springframework.stereotype.Service;
import pl.it.conf.scheduler.model.Lecture;
import pl.it.conf.scheduler.model.User;

@Service
public class Mapper {

    public UserDto mapUser(User user) {
        return new UserDto.UserDtoBuilder()
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }

    public LectureDto mapLecture(Lecture lecture) {
        return new LectureDto.LectureDtoBuilder()
                .lectureId(lecture.getLectureId())
                .startTime(lecture.getStartTime())
                .endTime(lecture.getEndTime())
                .theme(lecture.getTheme())
                .remainingCapacity(lecture.getRemainingCapacity())
                .build();
    }
}
