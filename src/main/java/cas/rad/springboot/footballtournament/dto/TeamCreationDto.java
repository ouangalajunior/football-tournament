package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Team;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TeamCreationDto {
    private String name;
    private String city;

    public Team toEntity(){
        return new Team(name,city);
    }
}
