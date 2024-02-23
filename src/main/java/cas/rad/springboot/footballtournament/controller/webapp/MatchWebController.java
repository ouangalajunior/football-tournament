package cas.rad.springboot.footballtournament.controller.webapp;

import cas.rad.springboot.footballtournament.dto.MatchCreattionDto;
import cas.rad.springboot.footballtournament.dto.TeamResponseDto;
import cas.rad.springboot.footballtournament.dto.TournamentCreationDto;
import cas.rad.springboot.footballtournament.dto.TournamentResponseDto;
import cas.rad.springboot.footballtournament.service.MatchService;
import cas.rad.springboot.footballtournament.service.TeamService;
import cas.rad.springboot.footballtournament.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}

