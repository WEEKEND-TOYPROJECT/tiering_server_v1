package toy.tiering.api.myteam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.myteam.domain.SportType;
import toy.tiering.api.myteam.dto.SearchCondition;

import java.util.List;

public interface SearchMyTeamRepository {

    public List<MyTeam> search(SearchCondition searchCondition);

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
