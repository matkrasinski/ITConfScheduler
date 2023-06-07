package pl.it.conf.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.it.conf.scheduler.dto.Mapper;
import pl.it.conf.scheduler.dto.ReservationDto;
import pl.it.conf.scheduler.exception.type.ForbiddenException;
import pl.it.conf.scheduler.model.Lecture;
import pl.it.conf.scheduler.model.Reservation;
import pl.it.conf.scheduler.model.User;
import pl.it.conf.scheduler.payload.request.UserArg;
import pl.it.conf.scheduler.payload.response.SimpleResponse;
import pl.it.conf.scheduler.repository.LectureRepository;
import pl.it.conf.scheduler.repository.ReservationRepository;
import pl.it.conf.scheduler.repository.UserRepository;
import pl.it.conf.scheduler.utils.EmailManager;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public SimpleResponse makeReservation(Long lectureId, UserArg userArg) {
        Optional<User> user = userRepository.findUserByLogin(userArg.getLogin());
        if (user.isEmpty())
            throw new IllegalArgumentException("User with this login does not exist");

        Optional<Lecture> lecture = lectureRepository.findById(lectureId);
        if (lecture.isEmpty())
            throw new IllegalArgumentException("Lecture with given ID does not exist");

        if (lecture.get().getRemainingCapacity() == 0)
            throw new ForbiddenException("All seats for the lecture have already been reserved");

        List<Reservation> userReservations = user.get().getReservations()
                        .stream()
                        .filter(e -> e.getLecture().getConferenceId() == lecture.get().getConferenceId())
                        .toList();
        if (userReservations.stream()
                .anyMatch(e -> checkTimeOverlap(e.getLecture().getStartTime(), e.getLecture().getEndTime(),
                        lecture.get().getStartTime(), lecture.get().getEndTime())))
            throw new ForbiddenException("You already have a reservation for the given time");

        Reservation reservation = reservationRepository.save(Reservation.builder()
                .user(user.get())
                .lecture(lecture.get())
                .build()
        );
        lecture.get().setRemainingCapacity(lecture.get().getRemainingCapacity() - 1);

        lectureRepository.save(lecture.get());

        EmailManager.sendEmail(LocalDateTime.now(), user.get().getEmail(), mapper.mapReservation(reservation));

        return new SimpleResponse(HttpStatus.OK.value(), "Reservation made successfully");
    }

    public List<ReservationDto> listReservations(String logic) {
        Optional<User> user = userRepository.findUserByLogin(logic);
        if (user.isEmpty())
            throw new IllegalArgumentException("User with this login does not exist");

        return user.get().getReservations().stream().map(mapper::mapReservation).toList();
    }

    public SimpleResponse cancelReservation(UserArg userArg, Long reservationId) {
        Optional<User> user = userRepository.findUserByLogin(userArg.getLogin());
        if (user.isEmpty())
            throw new IllegalArgumentException("User with this login does not exist");

        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty() || !user.get().getReservations().contains(reservation.get()))
            throw new IllegalArgumentException("You do not have this reservation");

        reservationRepository.delete(reservation.get());

        Lecture lecture = reservation.get().getLecture();
        lecture.setRemainingCapacity(lecture.getRemainingCapacity() + 1);
        lectureRepository.save(lecture);

        return new SimpleResponse(HttpStatus.ACCEPTED.value(), "Successfully canceled reservation");
    }

    private boolean checkTimeOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return !start1.isAfter(end2) && !end1.isBefore(start2);
    }
}
