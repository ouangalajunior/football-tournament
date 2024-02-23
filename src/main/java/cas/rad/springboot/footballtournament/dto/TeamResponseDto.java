package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TeamResponseDto {
    private Long id;
    private String name;
    private String city;
    private String logoUrl;

   // private List<TournamentResponseDto> tournamentsIds;

    public static TeamResponseDto fromEntity(Team team){
        return new TeamResponseDto(team.getId(), team.getName(),
                team.getCity(), team.getLogoUrl()

                //team.getTournaments().stream().map(TournamentResponseDto::fromEntity).toList()
                );
    }
}
