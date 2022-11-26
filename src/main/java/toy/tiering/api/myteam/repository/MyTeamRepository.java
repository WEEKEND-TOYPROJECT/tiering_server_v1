package toy.tiering.api.myteam.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import toy.tiering.api.myteam.domain.MyTeam;

import java.util.List;

public interface MyTeamRepository extends JpaRepository<MyTeam, Long>, SearchMyTeamRepository {
    @EntityGraph(attributePaths = "user")
    @Query("SELECT A FROM MyTeam A")
    List<MyTeam> getList();
}
