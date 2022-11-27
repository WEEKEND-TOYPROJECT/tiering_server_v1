package toy.tiering.api.player.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.player.domain.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long>, SearchPlayerRepository {

    Page<Player> findByPlayerTeamContaining(String keyword, Pageable pageable);

}
