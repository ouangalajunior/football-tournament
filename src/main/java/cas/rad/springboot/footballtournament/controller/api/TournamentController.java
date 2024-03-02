package cas.rad.springboot.footballtournament.controller.api;

import cas.rad.springboot.footballtournament.dto.TournamentCreationDto;
import cas.rad.springboot.footballtournament.dto.TournamentResponseDto;
import cas.rad.springboot.footballtournament.dto.TournamentUpdateDto;
import cas.rad.springboot.footballtournament.service.TournamentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("tournament-api")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    //Create tournament
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
public TournamentResponseDto create(@RequestBody TournamentCreationDto tournament){
    return tournamentService.create(tournament);
    }
    //Get all tournaments
    @GetMapping(value = "", produces = "application/json")
    public List<TournamentResponseDto> getAll(){
        return tournamentService.getAll();
    }

    //Get one tournament
    @GetMapping(value = "{id}", produces = "application/json")
    public TournamentResponseDto getOne(@PathVariable Long id){
        Optional<TournamentResponseDto> tournament = tournamentService.getOne(id);

        if(tournament.isPresent()){
            return tournament.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found");
        }

    }
/*
    @GetMapping("/list")
    public String listTournaments(Model model) {
        List<TournamentResponseDto> tournaments = tournamentService.getAll();
        model.addAttribute("tournaments", tournaments);
        return "list"; // Return the name of your Thymeleaf template
    }

 */

    // Tournament update
    /*
    @PutMapping(value = "{id}", consumes =  "application/json", produces = "application/json")
    public TournamentResponseDto update(@RequestBody TournamentUpdateDto dto, @PathVariable Long id){
        Optional<TournamentResponseDto> tournament = tournamentService.update(dto,id);
        if(tournament.isPresent()){
            return tournament.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone not found");
        }
    }

     */

}
