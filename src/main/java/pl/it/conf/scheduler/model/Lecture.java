package pl.it.conf.scheduler.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @NonNull
    private LocalTime startTime;

    @NonNull
    private LocalTime endTime;

    @NonNull
    private String theme;

    @NonNull
    private Integer remainingCapacity;

    @OneToMany
    @JoinColumn(name = "reservation_id")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
}
