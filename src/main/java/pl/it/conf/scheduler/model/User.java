package pl.it.conf.scheduler.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NonNull
    private String login;

    @NonNull
    private String email;

    @OneToMany
    @JoinColumn(name = "reservation_id")
    private List<Reservation> reservations;
}

