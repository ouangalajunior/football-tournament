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

    public Optional<TournamentResponseDto> update(TournamentResponseDto dto, Long id){
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
                .map(team -> new TeamResponseDto(team.getId(), team.getName(), team.getCity(), team.getLogoUrl()))
                .toList();
    }

    public Optional<TournamentResponseDto> addMatchToTournament(Long tournamentId, MatchCreattionDto matchDto) {
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


    //Add match to tournament
    /*
    public Optional<TournamentResponseDto> addMatchToTournament(Long tournamentId, MatchCreattionDto matchDto) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);

        if (tournamentOptional.isEmpty()) {
            return Optional.empty(); // Tournament not found
        }

        Tournament tournament = tournamentOptional.get();

        // Convert the MatchCreationDto to a Match entity
        Match match = matchDto.toEntity();

        // Set the tournament for the match
        match.setTournament(tournament);

        // Save the updated match entity
        matchRepository.save(match);

        return Optional.of(TournamentResponseDto.fromEntity(tournament));
    }
    */


    /*
    public Optional<TournamentResponseDto> addMatchesToTournament(Long tournamentId) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
        //Optional<Match> matchtOptional = matchRepository.findById(matchId);

        if (tournamentOptional.isEmpty()
                //|| matchtOptional.isEmpty())
        {
            return Optional.empty(); // Tournament not found
        }

        Tournament tournament = tournamentOptional.get();


        // Fetch match from the database based on teamIds
       // Match match = matchtOptional.get();

      match.setTournament(tournament);

        // Save the updated tournament entity
        matchRepository.save(match);

        return Optional.of(TournamentResponseDto.fromEntity(tournament));
    }

     */

    public  List<MatchResponseDto> getMacthesByTournamentId(Long tournamentId){
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
        if (tournamentOptional.isEmpty()) {
            throw new EntityNotFoundException("Tournament with ID " + tournamentId + " not found");
        }

        // Fetch match associated with the tournament
        Tournament tournament = tournamentOptional.get();
        List<Match> matches = tournament.getMatches();


        // Convert teams to DTOs
        return matches.stream()
                .map(match -> new MatchResponseDto(match.getId(),
                        match.getDate(),
                        match.getStartTime(),
                        match.getLocation(),
                        match.getHomeTeam().getId(),
                        match.getHomeTeam().getName(),
                        match.getAwayTeam().getId(),
                        match.getAwayTeam().getName(),
                        match.getTournament().getId()))
                .toList();

    }

}
