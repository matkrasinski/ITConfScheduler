package pl.it.conf.scheduler.payload.request;

import jakarta.validation.constraints.Email;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserArg {

    @NonNull
    private String login;

    @Email
    @NonNull
    private String email;

}
