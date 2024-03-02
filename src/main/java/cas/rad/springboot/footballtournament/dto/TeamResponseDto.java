package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class TeamResponseDto {
    private Long id;
    private String name;
    private String city;




    public static TeamResponseDto fromEntity(Team team){
        return new TeamResponseDto(team.getId(), team.getName(),
                team.getCity()


                );
    }
}
