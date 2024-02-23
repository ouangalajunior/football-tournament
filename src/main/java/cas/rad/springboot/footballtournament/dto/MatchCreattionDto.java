package cas.rad.springboot.footballtournament.dto;
import cas.rad.springboot.footballtournament.entity.Match;
import cas.rad.springboot.footballtournament.entity.Team;
import cas.rad.springboot.footballtournament.entity.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter

public class MatchCreattionDto {
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private String location;

    private Long homeTeamId;
    private Long awayTeamId;
    private Long tournamentId;

    public Match toEntity(Team homeTeam, Team awayTeam, Tournament tournament) {
        return new Match(description, date, startTime, location, tournament, homeTeam, awayTeam);
    }
}
