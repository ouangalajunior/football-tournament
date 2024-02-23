package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class MatchResponseDto {
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private String location;
    private Long homeTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private Long awayTeamId;
    private Long tournamentId;

    public MatchResponseDto(Long id, LocalDate date, LocalTime startTime, String location, Long homeTeamId, String homeTeamName, Long awayTeamId, String awayTeamName, Long tournamentId) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.homeTeamId = homeTeamId;
        this.homeTeamName = homeTeamName;
        this.awayTeamId = awayTeamId;
       this.awayTeamName = awayTeamName;
        this.tournamentId = tournamentId;
    }

    public static MatchResponseDto fromEntity(Match match){
        return new MatchResponseDto(
                match.getId(),
                match.getDate(),
                match.getStartTime(),
                match.getLocation(),
                match.getHomeTeam().getId(),
                match.getHomeTeam().getName(),
                match.getAwayTeam().getId(),
                match.getAwayTeam().getName(),
                match.getTournament().getId()
        );
    }
}
