package cas.rad.springboot.footballtournament.dto;

import cas.rad.springboot.footballtournament.entity.Tournament;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TournamentUpdateDto {
    private String name;
    private String location;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public TournamentUpdateDto toEntity(Tournament tournament){
        return new TournamentUpdateDto(tournament.getName(),
                tournament.getLocation(),
                tournament.getDescription(),
                tournament.getStartDate(),
                tournament.getEndDate()
        );
    }
}
