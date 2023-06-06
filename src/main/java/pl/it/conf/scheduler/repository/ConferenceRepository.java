package pl.it.conf.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.it.conf.scheduler.model.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {}
