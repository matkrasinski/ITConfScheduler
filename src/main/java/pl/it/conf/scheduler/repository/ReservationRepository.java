package pl.it.conf.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.it.conf.scheduler.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {}
