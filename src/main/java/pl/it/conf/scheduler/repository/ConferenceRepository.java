package pl.it.conf.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.it.conf.scheduler.model.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {}
