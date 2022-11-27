package toy.tiering.api.player.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.myteam.dto.SearchCondition;
import toy.tiering.api.player.domain.Player;

import java.util.List;

public interface SearchPlayerRepository {



   Page<Object[]> searchPage(String type, String keyword, Pageable pageable);


}
