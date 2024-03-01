package cas.rad.springboot.footballtournament.controller.webapp;

import cas.rad.springboot.footballtournament.dto.TeamCreationDto;
import cas.rad.springboot.footballtournament.dto.TeamResponseDto;
import cas.rad.springboot.footballtournament.dto.TournamentCreationDto;
import cas.rad.springboot.footballtournament.dto.TournamentResponseDto;
import cas.rad.springboot.footballtournament.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamWebController {
 private final TeamService teamService;

 //Get all team
 @GetMapping({"", "/"})
    public String getAllTeams(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "team/index";
    }

    //get one team
    @GetMapping("/{id}")
    public String getOneTeam(@PathVariable Long id, Model model){
        TeamResponseDto team = teamService.getOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team id: " + id));
        model.addAttribute("team", team);
        return "team/details";
    }


    //create team

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("team", new TeamCreationDto());
        return "team/create";
    }


    @PostMapping("/create")
    public String createTeam(@ModelAttribute("team") TeamCreationDto team) {
        teamService.create(team);
        return "redirect:/team/index";
    }





    //Edit team

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        TeamResponseDto team = teamService.getOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tournament id: " + id));
        model.addAttribute("team", team);
        return "team/edit";
    }


    @PostMapping("/edit/{id}")
    public String updateTeam(@ModelAttribute TeamResponseDto dto,@PathVariable Long id) {
        Optional<TeamResponseDto> team = teamService.update(dto,id);
        // model.addAttribute("tournament", tournament);

        //Optional<TournamentResponseDto> tournament = tournamentService.update(dto,id);
        //tournamentDto.setId(id); // Ensure the ID is set
        //tournamentService.update(id);
        return "redirect:/team/index";
    }



    //Delete team

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Long id) {
        teamService.deleteOne(id);
        return "redirect:/team/index";
    }




}
