package dsl.task02;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

class TomatoService {
    private final TomatoRepository tomatoRepository;

    TomatoService(TomatoRepository tomatoRepository) {
        this.tomatoRepository = tomatoRepository;
    }

    public List<Tomato> findAll(Tomato.Color color) {
        return tomatoRepository.findAll()
            .stream()
            .filter(tomato -> tomato.color() == color)
            .toList();
    }

    public List<Tomato> findAll(Tomato.Size size) {
        List<Tomato> list = tomatoRepository.findAll();
        List<Tomato> result = new ArrayList<>();

        for (Tomato tomato : list) {
            if (tomato.size() == size) {
                result.add(tomato);
            }
        }

        return result;
    }

    public List<Tomato> findAll(Tomato.Usage usage) {
        return tomatoRepository.findAll()
            .stream()
            .filter(tomato -> tomato.usage() == usage)
            .toList();
    }

    public List<Tomato> findAll(Tomato.Color color, Tomato.Size size) {
        List<Tomato> list = tomatoRepository.findAll();
        List<Tomato> result = new ArrayList<>();

        for (Tomato tomato : list) {
            if (tomato.size() == size && tomato.color() == color) {
                result.add(tomato);
            }
        }

        return result;
    }

    public List<Tomato> findAll(Tomato.Size size, Tomato.Usage usage) {
        return tomatoRepository.findAll()
            .stream()
            .filter(tomato -> tomato.size() == size && tomato.usage() == usage)
            .toList();
    }

    public List<Tomato> findAll(Tomato.Color color, Tomato.Size size, Tomato.Usage usage) {
        return tomatoRepository.findAll()
            .stream()
            .filter(tomato -> tomato.color() == color)
            .filter(tomato -> tomato.size() == size)
            .filter(tomato -> tomato.usage() == usage)
            .toList();
    }

    public final List<Tomato> findAll() {
        return tomatoRepository.findAll();
    }

    @SafeVarargs
    public final List<Tomato> findAllFilterAll(Predicate<Tomato>... predicates) {
        return tomatoRepository.findAll()
            .stream()
            .filter(combinedAll(predicates))
            .toList();
    }

    @SafeVarargs
    public final List<Tomato> findAllFilterAny(Predicate<Tomato>... predicates) {
        return tomatoRepository.findAll()
            .stream()
            .filter(combinedAny(predicates))
            .toList();
    }

    @SafeVarargs
    private static Predicate<Tomato> combinedAll(Predicate<Tomato>... predicates) {
        return Stream.of(predicates)
            .reduce(Predicate::and)
            .orElse(t -> false);
    }

    @SafeVarargs
    private static Predicate<Tomato> combinedAny(Predicate<Tomato>... predicates) {
        return Stream.of(predicates)
            .reduce(Predicate::or)
            .orElse(t -> false);
    }
}