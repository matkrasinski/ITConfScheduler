package pl.it.conf.scheduler.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.it.conf.scheduler.dto.Mapper;
import pl.it.conf.scheduler.dto.UserDto;
import pl.it.conf.scheduler.exception.type.ForbiddenException;
import pl.it.conf.scheduler.model.User;
import pl.it.conf.scheduler.payload.request.UserArg;
import pl.it.conf.scheduler.payload.response.SimpleResponse;
import pl.it.conf.scheduler.payload.response.UserResponse;
import pl.it.conf.scheduler.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${organizer.key}")
    private String organizerKey;
    private final UserRepository userRepository;
    private final Mapper mapper;


    public UserResponse register(UserArg request) {
        if (!userRepository.existsByLogin(request.getLogin())) {
            User user = userRepository.save(User.builder()
                            .login(request.getLogin())
                            .email(request.getEmail())
                            .build());

            return new UserResponse(
                    HttpStatus.CREATED.value(),
                    "Successfully registered user",
                    mapper.mapUser(user)
            );
        }
        throw new ValidationException("Given login is taken");
    }

    public SimpleResponse updateEmail(UserArg request) {
        var user = userRepository.findUserByLogin(request.getLogin());
        if (user.isEmpty())
            throw new IllegalArgumentException("User not found");

        user.get().setEmail(request.getEmail());

        userRepository.save(user.get());
        return new SimpleResponse(
                HttpStatus.OK.value(),
                "Successfully updated user email"
        );
    }

    public List<UserDto> displayAllUsers(String organizerKey) {
        if (organizerKey.isEmpty() || !Objects.equals(organizerKey, this.organizerKey))
            throw new ForbiddenException("You are not an organizer of conference");

        return userRepository.findAll().stream().map(mapper::mapUser).toList();
    }

}
