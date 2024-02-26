package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Match;
import cas.rad.springboot.footballtournament.entity.Team;
import cas.rad.springboot.footballtournament.entity.Tournament;
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
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private String location;


    public MatchUpdateDto toEntity(Match match) {
        return new MatchUpdateDto(
                match.getDescription(),
                match.getDate(),
                match.getStartTime(),
                match.getLocation()
        );
    }
}
