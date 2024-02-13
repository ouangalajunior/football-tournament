package cas.rad.springboot.footballtournament.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    //Define many-to-many relationship with Team

    @ManyToMany
    @JoinTable(
            name ="tournament_team",
            joinColumns = @JoinColumn(name="tournament_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    private List<Team> teams = new ArrayList<>();

    // Define relationship with matches in the tournament
    @OneToMany(mappedBy = "tournament")
    private List<Match> matches;
}
