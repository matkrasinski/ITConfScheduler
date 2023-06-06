package pl.it.conf.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.it.conf.scheduler.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {}
