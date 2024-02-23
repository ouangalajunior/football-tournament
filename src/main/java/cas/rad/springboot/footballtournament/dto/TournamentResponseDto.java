package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TournamentResponseDto {
    private Long id;
    private String name;
    private String location;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
   private List<TeamResponseDto> teams;
   private List<MatchResponseDto> matches;

    public  static TournamentResponseDto fromEntity(Tournament tournament){
        return new TournamentResponseDto(tournament.getId(),
                tournament.getName(),
                tournament.getLocation(),
                tournament.getDescription(),
                tournament.getStartDate(),
                tournament.getEndDate(),
                tournament.getTeams().stream().map(TeamResponseDto::fromEntity).toList(),
                tournament.getMatches().stream().map(MatchResponseDto::fromEntity).toList()
                )

        ;
    }
}
