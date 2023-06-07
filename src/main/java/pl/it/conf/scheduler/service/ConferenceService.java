package pl.it.conf.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.it.conf.scheduler.model.Conference;
import pl.it.conf.scheduler.repository.ConferenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;
    public List<Conference> getConferences() {
        return conferenceRepository.findAll();
    }

}
