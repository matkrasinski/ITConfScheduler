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
    @Column(name = "lecture_id")
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

    @NonNull
    @OneToMany(mappedBy = "lecture")
    private List<Reservation> reservations;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conferenceId;
}
