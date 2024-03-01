package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class TeamUpdateDto {
    private String name;
    private String city;

    public Team toEntity(){
        return new Team(name,city);
    }
}
