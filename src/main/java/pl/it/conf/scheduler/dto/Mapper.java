package pl.it.conf.scheduler.dto;

import org.springframework.stereotype.Service;
import pl.it.conf.scheduler.model.Lecture;
import pl.it.conf.scheduler.model.Reservation;
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

    public ReservationDto mapReservation(Reservation reservation) {
        return new ReservationDto.ReservationDtoBuilder()
                .reservationId(reservation.getReservationId())
                .lectureId(reservation.getLecture().getLectureId())
                .startTime(reservation.getLecture().getStartTime())
                .endTime(reservation.getLecture().getEndTime())
                .theme(reservation.getLecture().getTheme())
                .build();
    }

    public LectureWithAttendanceDto mapLectureWithAttendance(Lecture lecture, Integer maxSize) {
        return new LectureWithAttendanceDto.LectureWithAttendanceDtoBuilder()
                .lectureId(lecture.getLectureId())
                .startTime(lecture.getStartTime())
                .endTime(lecture.getEndTime())
                .theme(lecture.getTheme())
                .attendance(((double) Math.round(((double) maxSize - lecture.getRemainingCapacity()) / maxSize * 10000) / 100))
                .build();
    }
}
