package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Match;
import cas.rad.springboot.footballtournament.entity.MatchResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchResultCreationDto {
    private int homeTeamScore;
    private int awayTeamScore;
    private Long matchId;

    public MatchResult toEntity(Match match){
        return new MatchResult(homeTeamScore,awayTeamScore, match);
    }
}
