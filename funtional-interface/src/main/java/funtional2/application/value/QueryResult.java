package funtional2.application.value;


import lombok.Getter;

import java.util.Map;

@Getter
public final class QueryResult {

    private final Map<String, Object> queryResult;

    private QueryResult(Map<String, Object> queryResult) {
        this.queryResult = queryResult;
    }

    public static QueryResult from(Map<String, Object> queryResult) {
        return new QueryResult(queryResult);
    }
}