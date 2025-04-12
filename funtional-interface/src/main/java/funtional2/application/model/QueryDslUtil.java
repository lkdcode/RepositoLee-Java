package funtional2.application.model;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public final class QueryDslUtil {

    public OrderSpecifier<?>[] createOrderSpecifier(
        final Pageable pageable,
        final Function<String, Expression<? extends Comparable<?>>> path
    ) {
        return pageable.getSort()
            .stream()
            .filter(Objects::nonNull)
            .map(order -> {
                val direction = order.isAscending() ? Order.ASC : Order.DESC;
                val property = order.getProperty();

                if (path == null) {
                    return null;
                }

                return new OrderSpecifier<>(direction, path.apply(property));
            })
            .filter(Objects::nonNull)
            .toArray(OrderSpecifier[]::new);
    }

    public OrderSpecifier<?>[] createOrderSpecifier(
        final Pageable pageable,
        final Function<String, Expression<? extends Comparable<?>>> path,
        final OrderSpecifier<?>... conditions
    ) {
        return Stream.concat(
            pageable.getSort()
                .stream()
                .filter(Objects::nonNull)
                .map(order -> {
                    val direction = order.isAscending() ? Order.ASC : Order.DESC;
                    val property = order.getProperty();

                    if (path == null) {
                        return null;
                    }

                    return new OrderSpecifier<>(direction, path.apply(property));
                })
                .filter(Objects::nonNull)
                .toList()
                .stream()
            , Arrays.stream(conditions)
                .filter(Objects::nonNull)
                .toList()
                .stream()
        ).toArray(OrderSpecifier[]::new);
    }
}