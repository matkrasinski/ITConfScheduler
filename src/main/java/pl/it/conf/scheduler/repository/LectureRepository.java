package pl.it.conf.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.it.conf.scheduler.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {}
