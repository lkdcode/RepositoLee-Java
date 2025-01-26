package task02;

import java.util.List;
import java.util.Map;

class QueryService {
    Map<String, Map<String, List<Integer>>> getValues() {
        return Map.of(
            "A", Map.of("Apple", List.of(1, 2, 3, 4)),
            "B", Map.of("Banana", List.of(51, 21, 451, 62)),
            "C", Map.of("Cheery", List.of(457, 3152, 4567, 316)),
            "D", Map.of("Durian", List.of(679, 2346, 49))
        );
    }
}