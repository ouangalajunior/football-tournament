package cas.rad.springboot.footballtournament.entity;

import jakarta.persistence.*;
import lombok.*;
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
    @Column(columnDefinition = "TEXT")
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
    private List<Match> matches = new ArrayList<>();

    public Tournament(String name, String location, String description, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Method to remove a team from the tournament
    public void removeTeam(Team team) {
        teams.remove(team);
        team.getTournaments().remove(this); // Ensure to remove the tournament from the team's list as well
    }
}
