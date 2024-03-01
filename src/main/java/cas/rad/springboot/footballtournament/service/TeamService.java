package cas.rad.springboot.footballtournament.service;

import cas.rad.springboot.footballtournament.dto.TeamCreationDto;
import cas.rad.springboot.footballtournament.dto.TeamResponseDto;
import cas.rad.springboot.footballtournament.dto.TournamentCreationDto;
import cas.rad.springboot.footballtournament.dto.TournamentResponseDto;
import cas.rad.springboot.footballtournament.entity.Team;
import cas.rad.springboot.footballtournament.entity.Tournament;
import cas.rad.springboot.footballtournament.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    //Team creation
    public TeamResponseDto create(TeamCreationDto dto){
        Team team = dto.toEntity();
        teamRepository.save(team);

        return TeamResponseDto.fromEntity(team);
    }

    //get one team
    public Optional<TeamResponseDto> getOne(Long id){
        return teamRepository.findById(id).map(TeamResponseDto::fromEntity);
    }

    //Get all Team
    public List<TeamResponseDto> getAll(){
        return  teamRepository.findAll().stream().map(TeamResponseDto::fromEntity).toList();
    }


    public Optional<TeamResponseDto> update(TeamResponseDto dto, Long id){
        Optional<Team> teamOptional = teamRepository.findById(id);

        if(teamOptional.isEmpty()){
            return Optional.empty();
        }
        Team team = teamOptional.get();

        team.setName(dto.getName());
        team.setCity(dto.getCity());
       // team.setLogoUrl(dto.getLogoUrl());

        teamRepository.save(team);

        return Optional.of(TeamResponseDto.fromEntity(team));
    }

    //delete one tournament

    public void deleteOne(Long id){
        teamRepository.deleteById(id);
    }
}
