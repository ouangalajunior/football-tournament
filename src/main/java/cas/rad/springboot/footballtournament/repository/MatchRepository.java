package cas.rad.springboot.footballtournament.repository;

import cas.rad.springboot.footballtournament.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    //List<Match> findMatchesByTournamentOrderByDate();

    @Query("SELECT m FROM Match m JOIN FETCH m.homeTeam JOIN FETCH m.awayTeam")
    List<Match> findAllMatchesWithTeamNames();

    @Query("SELECT m FROM Match m JOIN FETCH m.homeTeam JOIN FETCH m.awayTeam JOIN FETCH m.result")
    List<Match> findAllMatchesWithTeamNamesAndScores();

    @Query("SELECT m FROM Match m JOIN FETCH m.homeTeam JOIN FETCH m.awayTeam JOIN FETCH m.result WHERE m.result IS NOT NULL")
    List<Match> findAllPlayedMatchesWithTeamNamesAndScores();
}
