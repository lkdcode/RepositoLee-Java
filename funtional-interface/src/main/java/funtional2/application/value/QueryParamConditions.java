package funtional2.application.value;


import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class QueryParamConditions {
    private final List<ParamCondition<?>> list;

    private QueryParamConditions(ParamCondition<?>... conditions) {
        this.list = Arrays.asList(conditions);
    }

    public static QueryParamConditions of(ParamCondition<?>... conditions) {
        return new QueryParamConditions(conditions);
    }

    public Stream<ParamCondition<?>> stream() {
        return new ArrayList<>(this.list)
            .stream();
    }
}