package pl.it.conf.scheduler.dto;

import lombok.*;

import java.time.LocalTime;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationDto {
    @NonNull
    private Long reservationId;

    @NonNull
    private Long lectureId;

    @NonNull
    private LocalTime startTime;

    @NonNull
    private LocalTime endTime;

    @NonNull
    private String theme;

}
