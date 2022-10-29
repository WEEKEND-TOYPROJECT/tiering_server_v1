package toy.tiering.api.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.tiering.api.team.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
