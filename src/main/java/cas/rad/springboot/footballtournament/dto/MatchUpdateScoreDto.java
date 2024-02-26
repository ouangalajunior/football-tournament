package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchUpdateScoreDto {

    private int homeTeamScore;
    private int awayTeamScore;
    //private Long homeTeamId;
   // private Long awayTeamId;



    public MatchUpdateScoreDto toEntity(Match match){
        return new MatchUpdateScoreDto(match.getHomeTeamScore(),
                match.getAwayTeamScore()
                );

    }


}
