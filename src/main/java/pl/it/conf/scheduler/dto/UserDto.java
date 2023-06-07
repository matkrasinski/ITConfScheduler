package pl.it.conf.scheduler.dto;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    @NonNull
    private String login;

    @NonNull
    private String email;
}
