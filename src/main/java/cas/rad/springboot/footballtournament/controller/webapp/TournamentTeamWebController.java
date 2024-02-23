package cas.rad.springboot.footballtournament.controller.webapp;

import cas.rad.springboot.footballtournament.dto.TeamResponseDto;
import cas.rad.springboot.footballtournament.dto.TournamentResponseDto;
import cas.rad.springboot.footballtournament.service.TeamService;
import cas.rad.springboot.footballtournament.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tournament/{tournamentId}/teams")
public class TournamentTeamWebController {
    private final TournamentService tournamentService;


    private final TeamService teamService;
    public TournamentTeamWebController(TournamentService tournamentService, TeamService teamService) {
        this.tournamentService = tournamentService;
        this.teamService = teamService;
    }

    // Show form to add teams to the tournament
    @GetMapping("/add")
    public String showAddTeamForm(@PathVariable("tournamentId") Long tournamentId, Model model) {
        // Fetch the tournament
        TournamentResponseDto tournament = tournamentService.getOne(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tournament id: " + tournamentId));

        // Fetch available teams to add to the tournament
        List<TeamResponseDto> availableTeams = teamService.getAll();

        model.addAttribute("tournament", tournament);
        model.addAttribute("availableTeams", availableTeams);
        return "tournament/add-team";
    }


    // Process form submission to add teams to the tournament
    @PostMapping("/add")
    public String addTeamsToTournament(@PathVariable("tournamentId") Long tournamentId,
                                       @RequestParam List<Long> teamIds) {
        // Add selected teams to the tournament
        tournamentService.addTeamsToTournament(tournamentId, teamIds);
        return "redirect:/tournament/index" ;
    }


}
