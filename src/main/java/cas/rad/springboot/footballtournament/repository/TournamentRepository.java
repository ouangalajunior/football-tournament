package cas.rad.springboot.footballtournament.repository;

import cas.rad.springboot.footballtournament.entity.Match;
import cas.rad.springboot.footballtournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {


}
