package pl.it.conf.scheduler.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import pl.it.conf.scheduler.dto.UserDto;

@Getter
@Setter
public class UserResponse extends SimpleResponse {
    @Schema(description = "User data")
    private UserDto user;
    public UserResponse(int status, String message, UserDto user) {
        super(status, message);
        this.user = user;
    }
}


