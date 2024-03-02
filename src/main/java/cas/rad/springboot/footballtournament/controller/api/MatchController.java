package cas.rad.springboot.footballtournament.controller.api;

import cas.rad.springboot.footballtournament.dto.MatchCreationDto;
import cas.rad.springboot.footballtournament.dto.MatchResponseDto;
import cas.rad.springboot.footballtournament.dto.MatchUpdateScoreDto;
import cas.rad.springboot.footballtournament.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("match-api")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    //Create Match

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public MatchResponseDto create(@RequestBody MatchCreationDto match){
        return matchService.create(match);
    }


    //Get all Matches
    @GetMapping(value = "", produces = "application/json")
    public List<MatchResponseDto> getAll(){
        return matchService.getAll();
    }

    //Get one match
    @GetMapping(value = "{id}", produces = "application/json")
    public MatchResponseDto getOne(@PathVariable Long id){
        Optional<MatchResponseDto> match = matchService.getOne(id);

        if(match.isPresent()){
            return match.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }

    }

    @PutMapping("/{id}")
    public MatchResponseDto updateMatchScore(@PathVariable Long id, @RequestBody MatchUpdateScoreDto dto) {
       Optional<MatchResponseDto> match = matchService.updateScore(dto,id);
        return match.get();
    }

    @GetMapping(value="/with-team-names", produces = "application/json")
    public List<MatchResponseDto> getAllMatchesWithTeamNames() {
        return matchService.getAllMatchesWithTeamNames();

    }

    /*
    public ResponseEntity<List<Match>> getAllMatchesWithTeamNames() {
        List<Match> matches = matchService.getAllMatchesWithTeamNames();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/with-team-names-and-scores")
    public ResponseEntity<List<Match>> getAllMatchesWithTeamNamesAndScores() {
        List<Match> matches = matchService.getAllMatchesWithTeamNamesAndScores();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/played-with-team-names-and-scores")
    public ResponseEntity<List<Match>> getAllPlayedMatchesWithTeamNamesAndScores() {
        List<Match> matches = matchService.getAllPlayedMatchesWithTeamNamesAndScores();
        return ResponseEntity.ok(matches);
    }

     */
}
