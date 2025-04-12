package funtional2.adapter.input.api;


import funtional2.adapter.output.port.QueryApplePort;
import funtional2.adapter.output.port.QueryBananaPort;
import funtional2.application.usecase.QueryUseCase;
import funtional2.application.value.QueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryFruitApi {
    private final QueryBananaPort queryBananaPort;
    private final QueryApplePort queryApplePort;

    @GetMapping("/api/fruits")
    public QueryDTO<?> getFruitList() {
        return QueryUseCase.execute()
            .addQuery(queryBananaPort.findAll())
            .addQuery(queryApplePort.findAll())
            .fetch();
    }
}