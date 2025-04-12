package funtional2.adapter.input.api;

import funtional2.adapter.output.port.QueryBananaPort;
import funtional2.application.usecase.QueryUseCase;
import funtional2.application.value.ParamCondition;
import funtional2.application.value.QueryDTO;
import funtional2.application.value.QueryParamConditions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequiredArgsConstructor
public class QueryBananaApi {
    private final QueryBananaPort queryBananaPort;

    @GetMapping("/api/bananas")
    public QueryDTO<?> getBananaList(
        @PageableDefault(size = 15, page = 0, sort = "id", direction = ASC) final Pageable pageable,

        @RequestParam(name = "value1", required = false) final String value1,
        @RequestParam(name = "value2", required = false) final BigDecimal value2,
        @RequestParam(name = "value3", required = false) final LocalDate value3
    ) {
        return QueryUseCase.execute()
            .addQuery(
                queryBananaPort.findAll(
                    pageable,
                    QueryParamConditions.of(
                        ParamCondition.of("value1", value1),
                        ParamCondition.of("value2", value2),
                        ParamCondition.of("value3", value3)
                    )
                )
            )
            .fetch();
    }

    @GetMapping("/api/bananas/{id}")
    public QueryDTO<?> getBanana(
        @PathVariable(name = "id") final Long id
    ) {
        return QueryUseCase.execute()
            .fetch();
    }
}