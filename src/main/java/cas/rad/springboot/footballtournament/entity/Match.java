package cas.rad.springboot.footballtournament.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private String location;

    //Define relationship with teams participating in the match

    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;

    // Define relationship with tournament
    @ManyToOne
    private Tournament tournament;

    //Define relationship with match result
    @OneToOne(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private MatchResult result;
}
