package toy.tiering.api.myteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.tiering.api.myteam.domain.MyTeam;

public interface MyTeamRepository extends JpaRepository<MyTeam, Long> {
}
