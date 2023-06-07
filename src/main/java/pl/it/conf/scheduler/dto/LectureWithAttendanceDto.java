package pl.it.conf.scheduler.dto;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class LectureWithAttendanceDto {
    @NonNull
    private Long lectureId;

    @NonNull
    private String theme;

    @NonNull
    private LocalTime startTime;

    @NonNull
    private LocalTime endTime;

    @NonNull
    private Double attendance;
}
