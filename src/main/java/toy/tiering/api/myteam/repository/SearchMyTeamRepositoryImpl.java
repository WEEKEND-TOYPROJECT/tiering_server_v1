package toy.tiering.api.myteam.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.myteam.domain.QMyTeam;
import toy.tiering.api.myteam.domain.SportType;
import toy.tiering.api.myteam.dto.SearchCondition;
import toy.tiering.api.user.domain.QUser;

import java.util.List;
import java.util.stream.Collectors;

public class SearchMyTeamRepositoryImpl extends QuerydslRepositorySupport implements SearchMyTeamRepository {

    public SearchMyTeamRepositoryImpl() {
        super(MyTeam.class);
    }

    @Override
    public List<MyTeam> search(SearchCondition searchCondition) {
        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        QMyTeam myTeam = QMyTeam.myTeam;
        QUser user = QUser.user;

        JPQLQuery<MyTeam> jpqlQuery = from(myTeam);
        jpqlQuery.leftJoin(user).on(myTeam.user.eq(user));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(myTeam, user);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = myTeam.myTeamId.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(myTeam.myTeamName.contains(keyword));
                        break;
                    case "n":
                        conditionBuilder.or(user.nickName.contains(keyword));
                        break;
                }
            }

            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(MyTeam.class, "myteam");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(myTeam);

        //page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();

        return new PageImpl<Object[]>(result.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, count);
    }
}



