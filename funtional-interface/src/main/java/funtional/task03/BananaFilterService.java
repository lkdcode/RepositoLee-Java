package funtional.task03;

import funtional.Banana;

import java.util.List;
import java.util.function.Predicate;

class BananaFilterService {
    public List<Banana> filteredBananas(List<Banana> list, Predicate<Banana> condition) {
        return list.stream()
            .filter(condition)
            .toList();
    }
}