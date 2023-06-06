package pl.it.conf.scheduler.payload.response;

import lombok.Getter;
import lombok.Setter;
import pl.it.conf.scheduler.dto.UserDto;

@Getter
@Setter
public class UserResponse extends SimpleResponse {
    private UserDto user;
    public UserResponse(int status, String message, UserDto user) {
        super(status, message);
        this.user = user;
    }
}


