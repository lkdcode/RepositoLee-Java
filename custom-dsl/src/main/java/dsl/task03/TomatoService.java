package dsl.task03;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

class TomatoService {
    private final TomatoRepository tomatoRepository;

    TomatoService(TomatoRepository tomatoRepository) {
        this.tomatoRepository = tomatoRepository;
    }

    public void persist(Tomato tomato, Users users) {
        if (users.type() == Users.Type.ANONYMOUS) {
            throw new IllegalArgumentException("Invalid Authority!");
        }

        if (users.username().toUpperCase().contains("DANCER")) {
            throw new IllegalArgumentException("Invalid username! 'DANCER' is only for tomatoes!");
        }

        if (users.username().length() > 5) {
            throw new IllegalArgumentException("Invalid username length!");
        }

        if (tomato.usage() != Tomato.Usage.DANCER) {
            throw new IllegalArgumentException("Invalid tomato usage");
        }

        tomatoRepository.save(tomato, users);
    }

    public Map<Users, List<Tomato>> findAll() {
        return tomatoRepository.findAll();
    }

    private static Predicate<Tomato> checkTomatoUsage() {
        return tomato -> tomato.usage() == Tomato.Usage.DANCER;
    }

    private static Predicate<Users> checkType() {
        return users -> users.type() == Users.Type.ANONYMOUS;
    }

    private static Predicate<Users> checkUsernameLength() {
        return users -> users.username().length() > 5;
    }

    private static Predicate<Users> checkUsername() {
        return users -> !users.username().equalsIgnoreCase("DANCER");
    }
}