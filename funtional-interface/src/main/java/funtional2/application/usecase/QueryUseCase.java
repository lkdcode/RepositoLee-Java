package funtional2.application.usecase;


import funtional2.application.model.QueryPort;
import funtional2.application.model.ValidAuthorityPort;
import funtional2.application.value.QueryDTO;
import funtional2.application.value.QueryPortList;
import funtional2.application.value.ValidAuthorityPortList;

public final class QueryUseCase {

    private final ValidAuthorityPortList validAuthorityPortList;
    private final QueryPortList queryPortList;

    private QueryUseCase() {
        this.validAuthorityPortList = ValidAuthorityPortList.init();
        this.queryPortList = QueryPortList.init();
    }

    public static QueryUseCase execute() {
        return new QueryUseCase();
    }

    public QueryUseCase addAuthority(final ValidAuthorityPort validAuthorityPort) {
        this.validAuthorityPortList.add(validAuthorityPort);
        return this;
    }

    public QueryUseCase addQuery(final QueryPort queryPort) {
        this.queryPortList.add(queryPort);
        return this;
    }

    public QueryDTO<?> fetch() {
        validAuthorityPortList.valid();
        return QueryDTO.ok(this.queryPortList.fetch().getQueryResult());
    }
}