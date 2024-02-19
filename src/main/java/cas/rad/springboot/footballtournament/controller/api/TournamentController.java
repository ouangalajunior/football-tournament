package cas.rad.springboot.footballtournament.controller;

import cas.rad.springboot.footballtournament.dto.TournamentCreationDto;
import cas.rad.springboot.footballtournament.dto.TournamentResponseDto;
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

@RequestMapping("tournament")
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

    @GetMapping("/list")
    public String listTournaments(Model model) {
        List<TournamentResponseDto> tournaments = tournamentService.getAll();
        model.addAttribute("tournaments", tournaments);
        return "list"; // Return the name of your Thymeleaf template
    }
}
