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
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private String location;
    private Long homeTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private Long awayTeamId;
    private Long tournamentId;
    private int homeTeamScore;
    private int awayTeamScore;

    public MatchResponseDto(Long id,String description,
                            LocalDate date,
                            LocalTime startTime,
                            String location,
                            Long homeTeamId,
                            String homeTeamName,
                            Long awayTeamId,
                            String awayTeamName,
                            Long tournamentId,
                            int homeTeamScore,
                            int awayTeamScore) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.homeTeamId = homeTeamId;
        this.homeTeamName = homeTeamName;
        this.awayTeamId = awayTeamId;
       this.awayTeamName = awayTeamName;
        this.tournamentId = tournamentId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public static MatchResponseDto fromEntity(Match match){
        return new MatchResponseDto(
                match.getId(),
                match.getDescription(),
                match.getDate(),
                match.getStartTime(),
                match.getLocation(),
                match.getHomeTeam().getId(),
                match.getHomeTeam().getName(),
                match.getAwayTeam().getId(),
                match.getAwayTeam().getName(),
                match.getTournament().getId(),
                match.getHomeTeamScore(),
                match.getAwayTeamScore()
        );
    }
}
