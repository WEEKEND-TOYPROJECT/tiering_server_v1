package toy.tiering.api.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.tiering.api.player.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
