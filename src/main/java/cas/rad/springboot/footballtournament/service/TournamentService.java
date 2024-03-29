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
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private  final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    //Tournament creation
    public TournamentResponseDto create(TournamentCreationDto dto){
        Tournament tournament = dto.toEntity();
        tournamentRepository.save(tournament);

        return TournamentResponseDto.fromEntity(tournament);
    }

    //get One tournament
    public Optional<TournamentResponseDto> getOne(Long id){
        return tournamentRepository.findById(id).map(TournamentResponseDto::fromEntity);
    }

    //Get all Tournament
    public List<TournamentResponseDto> getAll(){
        return  tournamentRepository.findAll().stream().map(TournamentResponseDto::fromEntity).toList();
    }

    //Tournament update

    public Optional<TournamentResponseDto> update(TournamentUpdateDto dto, Long id){
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(id);

        if(tournamentOptional.isEmpty()){
            return Optional.empty();
        }
        Tournament tournament = tournamentOptional.get();

        tournament.setName(dto.getName());
        tournament.setLocation(dto.getLocation());
        tournament.setDescription(dto.getDescription());
        tournament.setStartDate(dto.getStartDate());
        tournament.setEndDate(dto.getEndDate());

        tournamentRepository.save(tournament);

        return Optional.of(TournamentResponseDto.fromEntity(tournament));
    }

    //delete one tournament

    public void deleteOne(Long id){
        tournamentRepository.deleteById(id);
    }

    //Add teams to tournament
    public Optional<TournamentResponseDto> addTeamsToTournament(Long tournamentId, List<Long> teamIds) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);

        if (tournamentOptional.isEmpty()) {
            return Optional.empty(); // Tournament not found
        }
        Tournament tournament = tournamentOptional.get();

        // Fetch teams from the database based on teamIds
        List<Team> teams = teamRepository.findAllById(teamIds);

        // Associate fetched teams with the tournament
        tournament.getTeams().addAll(teams);

        // Save the updated tournament entity
        tournamentRepository.save(tournament);

        return Optional.of(TournamentResponseDto.fromEntity(tournament));
    }

    //Get teams by tournament
    public List<TeamResponseDto> getTeamsByTournamentId(Long tournamentId) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
        if (tournamentOptional.isEmpty()) {
            throw new EntityNotFoundException("Tournament with ID " + tournamentId + " not found");
        }

        // Fetch teams associated with the tournament
        Tournament tournament = tournamentOptional.get();
        List<Team> teams = tournament.getTeams();


        // Convert teams to DTOs
        return teams.stream()
                .map(team -> new TeamResponseDto(team.getId(), team.getName(), team.getCity()))
                .toList();
    }

    //Add match to tournament
    public Optional<TournamentResponseDto> addMatchToTournament(Long tournamentId, MatchCreationDto matchDto) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);

        if (tournamentOptional.isEmpty()) {
            return Optional.empty(); // Tournament not found
        }

        Tournament tournament = tournamentOptional.get();

        // Retrieve home team and away team based on IDs from the DTO
        Optional<Team> homeTeamOptional = teamRepository.findById(matchDto.getHomeTeamId());
        Optional<Team> awayTeamOptional = teamRepository.findById(matchDto.getAwayTeamId());

        if (homeTeamOptional.isEmpty() || awayTeamOptional.isEmpty()) {
            return Optional.empty(); // Home team or away team not found
        }

        Team homeTeam = homeTeamOptional.get();
        Team awayTeam = awayTeamOptional.get();

        // Create a new Match entity using the DTO and retrieved teams
        Match match = matchDto.toEntity(homeTeam, awayTeam, tournament);

        // Save the match entity
        matchRepository.save(match);

        return Optional.of(TournamentResponseDto.fromEntity(tournament));
    }



    // Remove team from tournament

    public void removeTeamFromTournament(Long tournamentId, Long teamId) {
        // Retrieve the tournament entity by its ID
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tournament ID"));

        // Retrieve the team entity by its ID
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team ID"));

        // Remove the team from the tournament
        tournament.removeTeam(team);

        // Save the updated tournament entity
        tournamentRepository.save(tournament);
    }



}
