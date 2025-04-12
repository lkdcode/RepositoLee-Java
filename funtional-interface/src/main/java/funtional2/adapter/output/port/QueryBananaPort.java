package funtional2.adapter.output.port;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import funtional2.application.model.QueryBase;
import funtional2.application.model.QueryDslUtil;
import funtional2.application.value.ParamCondition;
import funtional2.infrastructure.entity.BananaJpaEntity;
import funtional2.infrastructure.entity.QBananaJpaEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

@Service
public class QueryBananaPort extends QueryBase<BananaJpaEntity, QBananaJpaEntity> {
    private static final String KEY = "BANANA";
    private static final QBananaJpaEntity BANANA = QBananaJpaEntity.bananaJpaEntity;

    public QueryBananaPort(JPAQueryFactory factory, QueryDslUtil queryDslUtil) {
        super(factory, queryDslUtil);
    }

    @Override
    public QBananaJpaEntity getQClazz() {
        return BANANA;
    }

    @Override
    protected String key() {
        return KEY;
    }

    @Override
    protected Function<String, Expression<? extends Comparable<?>>> createEntityPath() {
        return e -> switch (e.trim().toUpperCase()) {
            case "VALUE1" -> BANANA.someValue1;
            case "VALUE2" -> BANANA.someValue2;
            case "VALUE3" -> BANANA.someValue3;

            default -> BANANA.id;
        };
    }

    @Override
    protected Function<ParamCondition<?>, BooleanExpression> getParamCondition() {
        return e -> switch (e.key().trim().toUpperCase()) {
            case "ID" -> BANANA.id.eq((Long) e.value());
            case "VALUE1" -> BANANA.someValue1.containsIgnoreCase((String) e.value());
            case "VALUE2" -> BANANA.someValue2.eq((BigDecimal) e.value());
            case "VALUE3" -> BANANA.someValue3.eq((LocalDate) e.value());

            default -> null;
        };
    }
}