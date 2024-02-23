package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamUpdateDto {
    private String name;
    private String city;
    private String logoUrl;
    public Team toEntity(){
        return new Team(name,city,logoUrl);
    }
}
