package toy.tiering.api.rank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.tiering.api.rank.domain.Rank;

public interface RankRepository extends JpaRepository<Rank, Long> {
}
