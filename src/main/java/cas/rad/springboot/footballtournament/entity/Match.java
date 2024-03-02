package cas.rad.springboot.footballtournament.entity;

import jakarta.persistence.*;
import lombok.*;
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
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private String location;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

    //Define relationship with teams participating in the match

    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;

    // Define relationship with tournament
    @ManyToOne
    private Tournament tournament;



    public Match(String description, LocalDate date,  LocalTime startTime,  String location, Tournament tournament, Team homeTeam, Team awayTeam) {
        this.description =description;
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.tournament = tournament;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamName = homeTeam.getName();
        this.awayTeamName = awayTeam.getName();
    }


}
