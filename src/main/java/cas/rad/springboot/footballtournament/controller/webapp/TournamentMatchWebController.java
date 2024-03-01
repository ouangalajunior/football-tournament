package cas.rad.springboot.footballtournament.controller.webapp;

import cas.rad.springboot.footballtournament.dto.*;
import cas.rad.springboot.footballtournament.service.MatchService;
import cas.rad.springboot.footballtournament.service.TeamService;
import cas.rad.springboot.footballtournament.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tournament/{tournamentId}/matches")
public class TournamentMatchWebController {
    private final MatchService matchService;
    private final TournamentService tournamentService;
    private final TeamService teamService;

    public TournamentMatchWebController(MatchService matchService, TournamentService tournamentService, TeamService teamService) {
        this.matchService = matchService;
        this.tournamentService = tournamentService;
        this.teamService = teamService;
    }

    @GetMapping("/create")
    public String showCreateMatchForm(@PathVariable("tournamentId") Long tournamentId, Model model) {
        TournamentResponseDto tournament = tournamentService.getOne(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tournament id: " + tournamentId));

        MatchCreationDto match = new MatchCreationDto();
        List<TeamResponseDto> teams = tournamentService.getTeamsByTournamentId(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("match", match);


        model.addAttribute("teams", teams);

        // Ensure tournamentId is added to the model
       model.addAttribute("tournamentId", tournamentId);

        return "match/create";
    }



    @PostMapping("/create")
    public String createMatch(@PathVariable("tournamentId") Long tournamentId, @ModelAttribute MatchCreationDto match) {


        tournamentService.addMatchToTournament(tournamentId, match);
        return "redirect:/tournament/" + tournamentId;
    }


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
    public String updateMatch(@ModelAttribute ("MatchUpdateDto dto") MatchUpdateDto dto, @PathVariable Long id, @PathVariable("tournamentId") Long tournamentId){
        // Update the match details
        matchService.updateMatch(dto,id);

        // Redirect to the tournament details page
        return "redirect:/tournament/" + tournamentId;
    }



    @GetMapping("/delete/{id}")
    public String deleteMatch(@PathVariable Long id, @PathVariable("tournamentId") Long tournamentId) {
        matchService.deleteOne(id);
        return "redirect:/tournament/" + tournamentId;
    }

    @GetMapping("/add-score/{id}")
    public String showUpdateScoreForm(@PathVariable Long id, @PathVariable("tournamentId") Long tournamentId, Model model){
        MatchResponseDto match = matchService.getOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match id: " + id));

        // Add match details and tournamentId to the model
        model.addAttribute("match", match);
        model.addAttribute("tournamentId", tournamentId);
        model.addAttribute("matchId", id);
        model.addAttribute("matchUpdateScoreDto", new MatchUpdateScoreDto()); // Ensure the model contains an empty MatchUpdateDto object

        return "match/score";
    }

    @PostMapping("/add-score/{id}")
    public String updateScoreMatch(@ModelAttribute("matchUpdateScoreDto") MatchUpdateScoreDto dto, @PathVariable Long id, @PathVariable("tournamentId") Long tournamentId){
        matchService.updateScore(dto, id);

        // Redirect to the tournament details page
       // return "redirect:/tournament/" + tournamentId;
        return "redirect:/tournament/" + tournamentId;
    }



}

