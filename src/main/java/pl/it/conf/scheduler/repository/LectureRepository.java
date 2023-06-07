package pl.it.conf.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.it.conf.scheduler.model.Conference;
import pl.it.conf.scheduler.model.Lecture;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findAllByConferenceId(Conference conferenceId);

}
