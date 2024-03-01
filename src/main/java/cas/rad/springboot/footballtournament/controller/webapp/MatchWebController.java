package cas.rad.springboot.footballtournament.controller.webapp;

import cas.rad.springboot.footballtournament.dto.MatchResponseDto;
import cas.rad.springboot.footballtournament.dto.TeamResponseDto;
import cas.rad.springboot.footballtournament.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchWebController {
    private final MatchService matchService;

    //Get all matches

    @GetMapping({"/index"})
    public String getAllMatch(Model model) {
        List<MatchResponseDto> matches = matchService.getAll();
        model.addAttribute("matches", matches);
        return "match/index";
    }

    //get one match
    @GetMapping("/{id}")
    public String getOneMatch(@PathVariable Long id, Model model){
        MatchResponseDto match = matchService.getOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match id: " + id));
        model.addAttribute("match", match);
        return "match/details";
    }




}
