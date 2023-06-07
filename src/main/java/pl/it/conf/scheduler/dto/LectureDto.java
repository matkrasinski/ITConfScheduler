package pl.it.conf.scheduler.dto;


import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class LectureDto {

    @NonNull
    private Long lectureId;

    @NonNull
    private LocalTime startTime;

    @NonNull
    private LocalTime endTime;

    @NonNull
    private String theme;

    @NonNull
    private Integer remainingCapacity;

}
