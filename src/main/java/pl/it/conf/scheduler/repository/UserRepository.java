package pl.it.conf.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.it.conf.scheduler.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);

    User findUserByLogin(String login);

}
