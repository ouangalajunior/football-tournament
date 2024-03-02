package cas.rad.springboot.footballtournament.controller.api;

import cas.rad.springboot.footballtournament.dto.TeamCreationDto;
import cas.rad.springboot.footballtournament.dto.TeamResponseDto;
import cas.rad.springboot.footballtournament.service.TeamService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("team-api")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    //Create Team
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public TeamResponseDto create(@RequestBody TeamCreationDto team){
        return teamService.create(team);
    }
    //Get all team
    @GetMapping(value = "", produces = "application/json")
    public List<TeamResponseDto> getAll(){
        return teamService.getAll();
    }

    //Get one team
    @GetMapping(value = "{id}", produces = "application/json")
    public TeamResponseDto getOne(@PathVariable Long id){
        Optional<TeamResponseDto> team = teamService.getOne(id);

        if(team.isPresent()){
            return team.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }

    }
}
