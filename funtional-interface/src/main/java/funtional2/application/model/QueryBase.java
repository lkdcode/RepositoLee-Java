package funtional2.application.model;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import funtional2.application.value.ParamCondition;
import funtional2.application.value.QueryParamConditions;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class QueryBase<E, Q extends EntityPathBase<E>> {
    private static final long DEFAULT_LIMIT = 15;

    protected final JPAQueryFactory factory;
    protected final QueryDslUtil queryDslUtil;

    protected QueryBase(JPAQueryFactory factory, QueryDslUtil queryDslUtil) {
        this.factory = factory;
        this.queryDslUtil = queryDslUtil;
    }

    public QueryPort findAll() {
        return () -> Map.of(key(), factory
            .selectFrom(getQClazz())
            .limit(DEFAULT_LIMIT)
            .fetch()
        );
    }

    public QueryPort findAll(final BooleanExpression... conditions) {
        return () -> Map.of(key(), factory
            .selectFrom(getQClazz())
            .where(conditions)
            .fetch()
        );
    }

    public QueryPort findAll(final QueryParamConditions conditions) {
        return () -> Map.of(key(), factory
            .selectFrom(getQClazz())
            .where(usingWhereCondition(conditions))
            .fetch()
        );
    }

    public QueryPort findAll(final Pageable pageable, final QueryParamConditions conditions) {
        final var result = factory
            .selectFrom(getQClazz())
            .where(usingWhereCondition(conditions))

            .orderBy(queryDslUtil.createOrderSpecifier(pageable, createEntityPath()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())

            .fetch();

        final var total = getTotalSize(usingWhereCondition(conditions));

        return () -> Map.of(key(), new PageImpl<>(result, pageable, total));
    }

    public QueryPort findAll(final Pageable pageable, final BooleanExpression... conditions) {
        final var result = factory
            .selectFrom(getQClazz())
            .where(conditions)

            .orderBy(queryDslUtil.createOrderSpecifier(pageable, createEntityPath()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())

            .fetch();

        final var total = getTotalSize(conditions);

        return () -> Map.of(key(), new PageImpl<>(result, pageable, total));
    }

    public long getTotalSize(final BooleanExpression... conditions) {
        return Optional.ofNullable(factory
            .select(getQClazz().count())
            .from(getQClazz())
            .where(conditions)
            .fetchOne()
        ).orElse(0L);
    }

    private BooleanExpression[] usingWhereCondition(final QueryParamConditions queryParamConditions) {
        return queryParamConditions.stream()
            .filter(keyAndValueNotNull())
            .map(getParamCondition())
            .filter(Objects::nonNull)
            .toArray(BooleanExpression[]::new);
    }

    private BooleanExpression[] usingWhereCondition(final QueryParamConditions queryParamConditions, final BooleanExpression... expressions) {
        return Stream.concat(
                queryParamConditions.stream()
                    .filter(keyAndValueNotNull())
                    .map(getParamCondition())
                    .filter(Objects::nonNull)
                    .toList()
                    .stream(),
                Arrays.stream(expressions)
                    .filter(Objects::nonNull)
                    .toList().stream())
            .toArray(BooleanExpression[]::new);
    }

    private static Predicate<ParamCondition<?>> keyAndValueNotNull() {
        return e -> e.key() != null && e.value() != null;
    }

    public abstract Q getQClazz();

    protected abstract String key();

    protected abstract Function<String, Expression<? extends Comparable<?>>> createEntityPath();

    protected abstract Function<ParamCondition<?>, BooleanExpression> getParamCondition();
}
