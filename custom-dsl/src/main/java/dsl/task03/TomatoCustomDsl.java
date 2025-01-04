package dsl.task03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

class TomatoCustomDsl {
    private final Tomato tomato;
    private final Users users;
    private final List<Predicate<Users>> usersConditionList;
    private final List<Predicate<Tomato>> tomatoConditionList;

    public TomatoCustomDsl(Tomato tomato, Users users, List<Predicate<Users>> usersConditionList, List<Predicate<Tomato>> tomatoConditionList) {
        this.tomato = tomato;
        this.users = users;
        this.usersConditionList = usersConditionList;
        this.tomatoConditionList = tomatoConditionList;
    }

    public static TomatoCustomDsl action(Tomato tomato, Users users) {
        return new TomatoCustomDsl(tomato, users, new ArrayList<>(), new ArrayList<>());
    }

    public TomatoCustomDsl validUsers(Predicate<Users> condition) {
        usersConditionList.add(condition);
        return this;
    }

    public TomatoCustomDsl validTomato(Predicate<Tomato> condition) {
        tomatoConditionList.add(condition);
        return this;
    }

    public void save(BiConsumer<Tomato, Users> target) {
        boolean valid = usersConditionList.stream().allMatch(condition -> condition.test(users))
            && tomatoConditionList.stream().allMatch(condition -> condition.test(tomato));

        if (!valid) {
            throw new IllegalArgumentException("INVALID!!!!!!!");
        }

        target.accept(tomato, users);
    }
}