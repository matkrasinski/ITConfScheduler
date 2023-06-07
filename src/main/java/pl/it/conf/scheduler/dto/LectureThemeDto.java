package pl.it.conf.scheduler.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class LectureThemeDto {
    @NonNull
    public String theme;

    @NonNull
    public Double interest;
}
