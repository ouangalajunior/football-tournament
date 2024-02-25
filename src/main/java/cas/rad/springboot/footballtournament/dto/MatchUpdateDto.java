package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchUpdateDto {

    private int homeTeamScore;
    private int awayTeamScore;
    //private Long homeTeamId;
   // private Long awayTeamId;



    public MatchUpdateDto toEntity(Match match){
        return new MatchUpdateDto(match.getHomeTeamScore(),
                match.getAwayTeamScore()
                );

    }


}
