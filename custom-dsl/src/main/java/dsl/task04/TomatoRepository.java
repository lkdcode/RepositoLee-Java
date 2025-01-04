package dsl.task04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TomatoRepository {

    private static final Map<Users, List<Tomato>> DATA_BASE = new HashMap<>();

    void save(Tomato tomato, Users users) {
        DATA_BASE.computeIfAbsent(users, k -> new ArrayList<>()).add(tomato);
    }

    void remove(Tomato tomato, Users users) {
        DATA_BASE.computeIfPresent(users, (u, list) -> {
            list.remove(tomato);
            return list;
        });
    }

    Map<Users, List<Tomato>> findAll(){
        return new HashMap<>(DATA_BASE);
    }
}