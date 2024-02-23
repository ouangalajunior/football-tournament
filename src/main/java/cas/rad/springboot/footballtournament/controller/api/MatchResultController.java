package cas.rad.springboot.footballtournament.controller.api;

import cas.rad.springboot.footballtournament.dto.MatchResultCreationDto;
import cas.rad.springboot.footballtournament.dto.MatchResultResponseDto;
import cas.rad.springboot.footballtournament.dto.TeamCreationDto;
import cas.rad.springboot.footballtournament.dto.TeamResponseDto;
import cas.rad.springboot.footballtournament.service.MatchResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("result")
@RequiredArgsConstructor
public class MatchResultController {
    private final MatchResultService matchResultService;
    //Create result

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public MatchResultResponseDto create(@RequestBody MatchResultCreationDto result){
        return matchResultService.create(result);
    }


    //Get all Result
    @GetMapping(value = "", produces = "application/json")
    public List<MatchResultResponseDto> getAll(){
        return matchResultService.getAll();
    }

    //Get one team
    @GetMapping(value = "{id}", produces = "application/json")
    public MatchResultResponseDto getOne(@PathVariable Long id){
        Optional<MatchResultResponseDto> result = matchResultService.getOne(id);

        if(result.isPresent()){
            return result.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found");
        }

    }
}
