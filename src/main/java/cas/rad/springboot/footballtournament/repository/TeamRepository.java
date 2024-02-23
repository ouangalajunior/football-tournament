package cas.rad.springboot.footballtournament.repository;

import cas.rad.springboot.footballtournament.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
