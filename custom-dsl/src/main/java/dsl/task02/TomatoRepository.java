package dsl.task02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dsl.task02.Tomato.Color.*;
import static dsl.task02.Tomato.Size.*;
import static dsl.task02.Tomato.Usage.*;

class TomatoRepository {
    private static final List<Tomato> DATA_BASE = BulkData.LIST;

    void save(Tomato tomato) {
        DATA_BASE.add(tomato);
    }

    void remove(Tomato tomato) {
        DATA_BASE.remove(tomato);
    }

    List<Tomato> findAll() {
        return new ArrayList<>(DATA_BASE);
    }

    private static class BulkData {
        private static final List<Tomato> LIST = Arrays.asList(
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(YELLOW, LARGE, KETCHUP),
            Tomato.of(YELLOW, LARGE, DANCER),
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(GREEN, LARGE, JUICE),
            Tomato.of(RED, SMALL, JUICE),
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(YELLOW, LARGE, KETCHUP),
            Tomato.of(RED, SMALL, DANCER),
            Tomato.of(YELLOW, LARGE, KETCHUP),
            Tomato.of(YELLOW, SMALL, JUICE),
            Tomato.of(GREEN, SMALL, KETCHUP),
            Tomato.of(BLUE, LARGE, JUICE),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(GREEN, SMALL, KETCHUP),
            Tomato.of(RED, LARGE, DANCER),
            Tomato.of(RED, SMALL, JUICE),
            Tomato.of(YELLOW, MEDIUM, KETCHUP),
            Tomato.of(BLUE, LARGE, DANCER),
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(BLUE, LARGE, DANCER),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(BLUE, LARGE, DANCER),
            Tomato.of(BLUE, LARGE, DANCER),
            Tomato.of(BLUE, SMALL, JUICE),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(YELLOW, MEDIUM, KETCHUP),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(GREEN, MEDIUM, DANCER),
            Tomato.of(YELLOW, LARGE, JUICE),
            Tomato.of(YELLOW, MEDIUM, KETCHUP),
            Tomato.of(RED, MEDIUM, KETCHUP),
            Tomato.of(BLUE, SMALL, JUICE),
            Tomato.of(GREEN, SMALL, KETCHUP),
            Tomato.of(BLUE, SMALL, DANCER),
            Tomato.of(RED, MEDIUM, JUICE),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(RED, SMALL, DANCER),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(RED, MEDIUM, KETCHUP),
            Tomato.of(YELLOW, SMALL, JUICE),
            Tomato.of(RED, LARGE, JUICE),
            Tomato.of(YELLOW, SMALL, JUICE),
            Tomato.of(RED, LARGE, JUICE),
            Tomato.of(RED, LARGE, JUICE),
            Tomato.of(YELLOW, SMALL, JUICE),
            Tomato.of(BLUE, MEDIUM, KETCHUP),
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(GREEN, MEDIUM, DANCER),
            Tomato.of(BLUE, MEDIUM, JUICE),
            Tomato.of(YELLOW, SMALL, DANCER),
            Tomato.of(RED, MEDIUM, DANCER),
            Tomato.of(RED, MEDIUM, JUICE),
            Tomato.of(GREEN, SMALL, DANCER),
            Tomato.of(BLUE, LARGE, KETCHUP),
            Tomato.of(RED, MEDIUM, JUICE),
            Tomato.of(GREEN, SMALL, DANCER),
            Tomato.of(YELLOW, SMALL, DANCER),
            Tomato.of(GREEN, LARGE, JUICE),
            Tomato.of(GREEN, LARGE, KETCHUP),
            Tomato.of(BLUE, SMALL, KETCHUP),
            Tomato.of(BLUE, MEDIUM, KETCHUP),
            Tomato.of(BLUE, MEDIUM, JUICE),
            Tomato.of(GREEN, MEDIUM, KETCHUP),
            Tomato.of(GREEN, SMALL, DANCER),
            Tomato.of(YELLOW, SMALL, JUICE),
            Tomato.of(RED, LARGE, JUICE),
            Tomato.of(GREEN, MEDIUM, KETCHUP),
            Tomato.of(BLUE, MEDIUM, KETCHUP),
            Tomato.of(RED, MEDIUM, KETCHUP),
            Tomato.of(BLUE, MEDIUM, DANCER),
            Tomato.of(GREEN, MEDIUM, KETCHUP),
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(BLUE, MEDIUM, DANCER),
            Tomato.of(YELLOW, LARGE, KETCHUP),
            Tomato.of(GREEN, SMALL, KETCHUP),
            Tomato.of(GREEN, LARGE, KETCHUP),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(YELLOW, MEDIUM, JUICE),
            Tomato.of(GREEN, LARGE, JUICE),
            Tomato.of(YELLOW, MEDIUM, KETCHUP),
            Tomato.of(RED, MEDIUM, DANCER),
            Tomato.of(YELLOW, SMALL, JUICE),
            Tomato.of(GREEN, SMALL, DANCER),
            Tomato.of(RED, MEDIUM, JUICE),
            Tomato.of(GREEN, SMALL, JUICE),
            Tomato.of(YELLOW, LARGE, JUICE),
            Tomato.of(RED, MEDIUM, DANCER),
            Tomato.of(BLUE, MEDIUM, KETCHUP),
            Tomato.of(GREEN, MEDIUM, DANCER),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(BLUE, LARGE, JUICE),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(YELLOW, SMALL, JUICE),
            Tomato.of(RED, MEDIUM, JUICE),
            Tomato.of(YELLOW, LARGE, JUICE),
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(BLUE, MEDIUM, KETCHUP),
            Tomato.of(RED, SMALL, JUICE),
            Tomato.of(YELLOW, LARGE, DANCER),
            Tomato.of(RED, SMALL, DANCER),
            Tomato.of(BLUE, SMALL, JUICE),
            Tomato.of(GREEN, LARGE, DANCER),
            Tomato.of(RED, SMALL, JUICE),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(YELLOW, SMALL, KETCHUP),
            Tomato.of(GREEN, LARGE, JUICE),
            Tomato.of(BLUE, SMALL, JUICE),
            Tomato.of(RED, SMALL, DANCER),
            Tomato.of(RED, MEDIUM, KETCHUP),
            Tomato.of(BLUE, MEDIUM, DANCER),
            Tomato.of(RED, MEDIUM, DANCER),
            Tomato.of(BLUE, SMALL, KETCHUP),
            Tomato.of(RED, SMALL, JUICE),
            Tomato.of(BLUE, LARGE, KETCHUP),
            Tomato.of(RED, MEDIUM, JUICE),
            Tomato.of(GREEN, LARGE, KETCHUP),
            Tomato.of(RED, SMALL, JUICE),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(YELLOW, MEDIUM, DANCER),
            Tomato.of(GREEN, MEDIUM, DANCER),
            Tomato.of(GREEN, LARGE, KETCHUP),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(YELLOW, MEDIUM, JUICE),
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(BLUE, SMALL, JUICE),
            Tomato.of(YELLOW, SMALL, DANCER),
            Tomato.of(BLUE, SMALL, JUICE),
            Tomato.of(GREEN, SMALL, DANCER),
            Tomato.of(YELLOW, SMALL, DANCER),
            Tomato.of(YELLOW, SMALL, KETCHUP),
            Tomato.of(RED, MEDIUM, DANCER),
            Tomato.of(RED, MEDIUM, KETCHUP),
            Tomato.of(GREEN, SMALL, KETCHUP),
            Tomato.of(YELLOW, MEDIUM, KETCHUP),
            Tomato.of(YELLOW, LARGE, JUICE),
            Tomato.of(RED, SMALL, DANCER),
            Tomato.of(GREEN, LARGE, JUICE),
            Tomato.of(RED, SMALL, KETCHUP),
            Tomato.of(BLUE, LARGE, JUICE),
            Tomato.of(GREEN, MEDIUM, DANCER),
            Tomato.of(BLUE, MEDIUM, DANCER),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(GREEN, SMALL, DANCER),
            Tomato.of(BLUE, MEDIUM, DANCER),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(GREEN, LARGE, DANCER),
            Tomato.of(GREEN, LARGE, KETCHUP),
            Tomato.of(BLUE, LARGE, DANCER),
            Tomato.of(GREEN, SMALL, KETCHUP),
            Tomato.of(RED, MEDIUM, KETCHUP),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(RED, SMALL, JUICE),
            Tomato.of(YELLOW, MEDIUM, DANCER),
            Tomato.of(RED, MEDIUM, JUICE),
            Tomato.of(RED, LARGE, KETCHUP),
            Tomato.of(YELLOW, LARGE, KETCHUP),
            Tomato.of(BLUE, SMALL, KETCHUP),
            Tomato.of(GREEN, MEDIUM, KETCHUP),
            Tomato.of(GREEN, MEDIUM, JUICE),
            Tomato.of(YELLOW, SMALL, JUICE),
            Tomato.of(BLUE, MEDIUM, JUICE),
            Tomato.of(BLUE, SMALL, KETCHUP),
            Tomato.of(GREEN, LARGE, DANCER),
            Tomato.of(BLUE, SMALL, JUICE),
            Tomato.of(RED, SMALL, KETCHUP)
        );
    }
}