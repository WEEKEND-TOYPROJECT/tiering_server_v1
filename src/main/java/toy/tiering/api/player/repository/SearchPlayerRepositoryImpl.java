package toy.tiering.api.player.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.player.domain.Player;
import toy.tiering.api.player.domain.QPlayer;
import toy.tiering.api.user.domain.QUser;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPlayerRepositoryImpl extends QuerydslRepositorySupport implements SearchPlayerRepository {
    public SearchPlayerRepositoryImpl() {
        super(Player.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        QPlayer player = QPlayer.player;

        JPQLQuery<Player> jpqlQuery = from(player);

        JPQLQuery<Tuple> tuple = jpqlQuery.select(player.playerId, player.playerName, player.playerPhoto, player.playerNation,

                player.playerPosition, player.playerSeason, player.playerStat, player.playerTeamLogo, player.playerTeam, player.modDate, player.regDate, player.sportType, player.myTeam.myTeamId); // 추출 항목



        tuple.where(player.playerId.gt(0L)); // where 조건

        if (type != null) {

            switch (type) {

                case "name":
                    tuple.where(player.playerTeam.contains(keyword));
                    break;

            }
        }

        tuple.orderBy(player.playerId.desc()); // order by 조건

        //page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> tupleList = tuple.fetch();

        List<Object[]> resultList = new ArrayList();

        for (Tuple t : tupleList) {

            resultList.add(t.toArray());

        }
        long count = tuple.fetchCount();

        return new PageImpl<Object[]>(resultList, pageable, count);

    }
}
