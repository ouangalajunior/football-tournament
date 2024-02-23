package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.MatchResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchResultResponseDto {
    private Long id;
    private int homeTeamScore;
    private int awayTeamScore;
    private Long matchId;

    public static MatchResultResponseDto fromEntity(MatchResult matchResult){
        return new MatchResultResponseDto(matchResult.getId(), matchResult.getHomeTeamScore(), matchResult.getAwayTeamScore(), matchResult.getMatch().getId());
    }
}
