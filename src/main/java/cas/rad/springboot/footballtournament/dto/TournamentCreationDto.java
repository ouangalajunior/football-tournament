package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Tournament;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TournamentCreationDto {
    private String name;
    private String location;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Tournament toEntity(){
        return new Tournament(name,location,description,startDate,endDate);
    }

}
