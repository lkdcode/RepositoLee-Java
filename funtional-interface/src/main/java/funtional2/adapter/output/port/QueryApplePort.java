package funtional2.adapter.output.port;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import funtional2.application.model.QueryBase;
import funtional2.application.model.QueryDslUtil;
import funtional2.application.value.ParamCondition;
import funtional2.infrastructure.entity.AppleJpaEntity;
import funtional2.infrastructure.entity.QAppleJpaEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Function;

@Service
public class QueryApplePort extends QueryBase<AppleJpaEntity, QAppleJpaEntity> {
    private static final String KEY = "APPLE";
    private static final QAppleJpaEntity APPLE = QAppleJpaEntity.appleJpaEntity;

    public QueryApplePort(JPAQueryFactory factory, QueryDslUtil queryDslUtil) {
        super(factory, queryDslUtil);
    }

    @Override
    public QAppleJpaEntity getQClazz() {
        return APPLE;
    }

    @Override
    protected String key() {
        return KEY;
    }

    @Override
    protected Function<String, Expression<? extends Comparable<?>>> createEntityPath() {
        return e -> switch (e.trim().toUpperCase()) {
            case "VALUE1" -> APPLE.someValue1;
            case "VALUE2" -> APPLE.someValue2;
            case "VALUE3" -> APPLE.someValue3;

            default -> APPLE.id;
        };
    }

    @Override
    protected Function<ParamCondition<?>, BooleanExpression> getParamCondition() {
        return e -> switch (e.key().trim().toUpperCase()) {
            case "ID" -> APPLE.id.eq((Long) e.value());
            case "VALUE1" -> APPLE.someValue1.containsIgnoreCase((String) e.value());
            case "VALUE2" -> APPLE.someValue2.eq((Integer) e.value());
            case "VALUE3" -> APPLE.someValue3.eq((LocalDateTime) e.value());

            default -> null;
        };
    }
}