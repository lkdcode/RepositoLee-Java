package funtional2.application.value;


import funtional2.application.model.QueryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class QueryPortList {
    private final List<QueryPort> list;

    private QueryPortList() {
        this.list = new ArrayList<>();
    }

    public static QueryPortList init() {
        return new QueryPortList();
    }

    public void add(final QueryPort port) {
        this.list.add(port);
    }

    public QueryResult fetch() {
        return QueryResult.from(
            list.stream()
                .map(QueryPort::read)
                .collect(Collectors.flatMapping(map -> map.entrySet().stream(),
                    Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                    )))
        );
    }
}