package cas.rad.springboot.footballtournament.controller.webapp;

import cas.rad.springboot.footballtournament.dto.*;
import cas.rad.springboot.footballtournament.service.MatchService;
import cas.rad.springboot.footballtournament.service.TeamService;
import cas.rad.springboot.footballtournament.service.TournamentService;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tournament/{tournamentId}/matches")
public class MatchWebController {
    private final MatchService matchService;
    private final TournamentService tournamentService;
    private final TeamService teamService;

    public MatchWebController(MatchService matchService, TournamentService tournamentService, TeamService teamService) {
        this.matchService = matchService;
        this.tournamentService = tournamentService;
        this.teamService = teamService;
    }

    @GetMapping("/create")
    public String showCreateMatchForm(@PathVariable("tournamentId") Long tournamentId, Model model) {
        TournamentResponseDto tournament = tournamentService.getOne(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tournament id: " + tournamentId));

        MatchCreattionDto match = new MatchCreattionDto();
        List<TeamResponseDto> teams = tournamentService.getTeamsByTournamentId(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("match", match);


        model.addAttribute("teams", teams);

        // Ensure tournamentId is added to the model
       model.addAttribute("tournamentId", tournamentId);

        return "match/create";
    }



    @PostMapping("/create")
    public String createMatch(@PathVariable("tournamentId") Long tournamentId, @ModelAttribute MatchCreattionDto match) {


        tournamentService.addMatchToTournament(tournamentId, match);
        return "redirect:/tournament/" + tournamentId;
    }

    /*
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, @PathVariable("tournamentId") Long tournamentId, Model model){
        // Retrieve the match details
        MatchResponseDto match = matchService.getOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match id: " + id));

        // Add match details and tournamentId to the model
        model.addAttribute("match", match);
        model.addAttribute("tournamentId", tournamentId);
        model.addAttribute("matchId", id);


        // Return the name of the Thymeleaf template for the edit form
        return "match/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateMatch(@ModelAttribute MatchUpdateDto dto, @PathVariable Long id, @PathVariable("tournamentId") Long tournamentId){
        // Update the match details
        matchService.update(dto, id);

        // Redirect to the tournament details page
        return "redirect:/tournament/" + tournamentId;
    }


     */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, @PathVariable("tournamentId") Long tournamentId, Model model){
        MatchResponseDto match = matchService.getOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match id: " + id));

        // Add match details and tournamentId to the model
        model.addAttribute("match", match);
        model.addAttribute("tournamentId", tournamentId);
        model.addAttribute("matchId", id);
        model.addAttribute("matchUpdateDto", new MatchUpdateDto()); // Ensure the model contains an empty MatchUpdateDto object

        return "match/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateMatch(@ModelAttribute("matchUpdateDto") MatchUpdateDto dto, @PathVariable Long id, @PathVariable("tournamentId") Long tournamentId){
        matchService.update(dto, id);

        // Redirect to the tournament details page
        return "redirect:/tournament/" + tournamentId;
    }



}

