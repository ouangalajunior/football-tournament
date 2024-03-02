package cas.rad.springboot.footballtournament.service;

import cas.rad.springboot.footballtournament.dto.*;
import cas.rad.springboot.footballtournament.entity.Match;
import cas.rad.springboot.footballtournament.entity.Team;
import cas.rad.springboot.footballtournament.entity.Tournament;
import cas.rad.springboot.footballtournament.repository.MatchRepository;
import cas.rad.springboot.footballtournament.repository.TeamRepository;
import cas.rad.springboot.footballtournament.repository.TournamentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;




    public MatchResponseDto create(MatchCreationDto dto) {
        // Retrieve home team, away team, and tournament from database using IDs
        Optional<Team> homeTeamOptional = teamRepository.findById(dto.getHomeTeamId());
        Optional<Team> awayTeamOptional = teamRepository.findById(dto.getAwayTeamId());
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(dto.getTournamentId());

        if (homeTeamOptional.isEmpty() || awayTeamOptional.isEmpty() || tournamentOptional.isEmpty()) {
            throw new EntityNotFoundException("One or more entities not found");
        }

        Team homeTeam = homeTeamOptional.get();
        Team awayTeam = awayTeamOptional.get();
        Tournament tournament = tournamentOptional.get();

        // Convert DTO to entity
        Match match = dto.toEntity(homeTeam, awayTeam, tournament);

        // Save match entity to the database
        match = matchRepository.save(match);

        // Convert match entity to response DTO
        return MatchResponseDto.fromEntity(match);
    }

    //get One match
    public Optional<MatchResponseDto> getOne(Long id){
        return matchRepository.findById(id).map(MatchResponseDto::fromEntity);
    }

    //Get all Match
    public List<MatchResponseDto> getAll(){
        return  matchRepository.findAll().stream().map(MatchResponseDto::fromEntity).toList();
    }
    // get matches with teams' name

    public List<MatchResponseDto> getAllMatchesWithTeamNames() {
        return matchRepository.findAllMatchesWithTeamNames()
                .stream()
                .map(MatchResponseDto::fromEntity)
                .toList();
    }

//Add score to match
    public Optional<MatchResponseDto> updateScore(MatchUpdateScoreDto dto, Long id){
        Optional<Match> matchOptional= matchRepository.findById(id);
        if(matchOptional.isEmpty()){
            return Optional.empty();
        }
        Match match = matchOptional.get();
        match.setHomeTeamScore(dto.getHomeTeamScore());
        match.setAwayTeamScore(dto.getAwayTeamScore());

        matchRepository.save(match);
        return Optional.of(MatchResponseDto.fromEntity(match));
    }

    //Update match
    public Optional<MatchResponseDto> updateMatch(MatchUpdateDto dto, Long id){
        Optional<Match> matchOptional= matchRepository.findById(id);
        if(matchOptional.isEmpty()){
            return Optional.empty();
        }
        Match match = matchOptional.get();

        match.setDescription(dto.getDescription());
        match.setDate(dto.getDate());
        match.setStartTime(dto.getStartTime());
        match.setLocation(dto.getLocation());


        matchRepository.save(match);
        return Optional.of(MatchResponseDto.fromEntity(match));
    }

    //delete match

    public void deleteOne(Long id){
        matchRepository.deleteById(id);
    }


}
