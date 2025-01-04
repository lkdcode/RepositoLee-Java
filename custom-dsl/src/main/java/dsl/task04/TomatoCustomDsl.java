package dsl.task04;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

class TomatoCustomDsl {
    private final Tomato tomato;
    private final Users users;

    public TomatoCustomDsl(Tomato tomato, Users users) {
        this.tomato = tomato;
        this.users = users;
    }

    public static TomatoCustomDsl action(Tomato tomato, Users users) {
        return new TomatoCustomDsl(tomato, users);
    }

    public TomatoCustomDsl validUsers(Consumer<Users> valid) {
        valid.accept(users);
        return this;
    }

    public TomatoCustomDsl validTomato(Consumer<Tomato> valid) {
        valid.accept(tomato);
        return this;
    }

    public void save(BiConsumer<Tomato, Users> target) {
        target.accept(tomato, users);
    }
}