package task01;

import java.util.List;
import java.util.Map;

class CommandService {

    Integer totalSum(final Map<String, Map<String, List<Integer>>> value) {
        return value.values()
            .stream()
            .flatMap(group -> group.values().stream())
            .flatMap(List::stream)
            .mapToInt(Integer::intValue)
            .sum();
    }
}