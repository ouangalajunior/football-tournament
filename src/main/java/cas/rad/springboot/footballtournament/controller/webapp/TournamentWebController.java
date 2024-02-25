package cas.rad.springboot.footballtournament.controller.webapp;

import cas.rad.springboot.footballtournament.dto.TournamentCreationDto;
import cas.rad.springboot.footballtournament.dto.TournamentResponseDto;
import cas.rad.springboot.footballtournament.dto.TournamentUpdateDto;
import cas.rad.springboot.footballtournament.service.TeamService;
import cas.rad.springboot.footballtournament.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tournament")
public class TournamentWebController {
    private final TournamentService tournamentService;


    public TournamentWebController(TournamentService tournamentService, TeamService teamService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping({"", "/"})
    public String getAllTournaments(Model model){
        model.addAttribute("tournaments", tournamentService.getAll());
        return "tournament/index";
    }

    @GetMapping("/{id}")
    public String getOneTournament(@PathVariable Long id, Model model){
        TournamentResponseDto tournament = tournamentService.getOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tournament id: " + id));
        model.addAttribute("tournament", tournament);
        return "tournament/details";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("tournament", new TournamentCreationDto());
        return "tournament/create";
    }

    @PostMapping("/create")
    public String createTournament(@ModelAttribute("tournament") TournamentCreationDto tournament) {
        tournamentService.create(tournament);
        return "redirect:/tournament/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        TournamentResponseDto tournament = tournamentService.getOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tournament id: " + id));
        model.addAttribute("tournament", tournament);
        return "tournament/edit";
    }


    @PostMapping("/edit/{id}")
    public String updateTournament(@ModelAttribute TournamentUpdateDto dto, @PathVariable Long id) {
        tournamentService.update(dto,id);
       // model.addAttribute("tournament", tournament);

        //Optional<TournamentResponseDto> tournament = tournamentService.update(dto,id);
        //tournamentDto.setId(id); // Ensure the ID is set
        //tournamentService.update(id);
        return "redirect:/tournament/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTournament(@PathVariable Long id) {
        tournamentService.deleteOne(id);
        return "redirect:/tournament/";
    }

    //Add teams to the tournament


}
