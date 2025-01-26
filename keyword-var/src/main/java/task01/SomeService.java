package task01;

import java.util.List;
import java.util.Map;

class SomeService {
    private final QueryService queryService = new QueryService();
    private final CommandService commandService = new CommandService();

    public Boolean someMethod() {
        final Map<String, Map<String, List<Integer>>> values = queryService.getValues();
        final Integer result = commandService.totalSum(values);
        return result % 2 == 0;
    }
}