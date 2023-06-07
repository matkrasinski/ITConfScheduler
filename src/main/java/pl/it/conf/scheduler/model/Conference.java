package pl.it.conf.scheduler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conferences")
public class Conference {

    @Id
    @Column(name = "conference_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conferenceId;

    @NonNull
    private String title;

    @NonNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

}
