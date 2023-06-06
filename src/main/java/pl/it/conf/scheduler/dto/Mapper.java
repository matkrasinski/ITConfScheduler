package pl.it.conf.scheduler.dto;

import org.springframework.stereotype.Service;
import pl.it.conf.scheduler.model.User;

@Service
public class Mapper {

    public UserDto mapUser(User user) {
        return new UserDto.UserDtoBuilder()
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }
}
