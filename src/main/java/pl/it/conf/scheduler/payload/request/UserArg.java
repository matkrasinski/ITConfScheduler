package pl.it.conf.scheduler.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserArg {

    @NonNull
    @Schema(description = "User login", example = "example")
    private String login;

    @Email
    @NonNull
    @Schema(description = "User email", example = "example@gmail.com")
    private String email;

}
