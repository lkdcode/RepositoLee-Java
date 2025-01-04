package dsl.task02;

import java.util.List;
import java.util.function.Supplier;

class TomatoController {
    private final TomatoService tomatoService;

    TomatoController(TomatoService tomatoService) {
        this.tomatoService = tomatoService;
    }
s
    public void retrieveAll() {
        print(tomatoService::findAll);
    }

    public void retrieveAllFilterAll() {
        print(() ->
            tomatoService.findAllFilterAll(
                tomato -> tomato.color() == Tomato.Color.RED,
                tomato -> tomato.size() == Tomato.Size.LARGE,
                tomato -> tomato.usage() == Tomato.Usage.DANCER
            )
        );
    }

    public void retrieveAllFilterAny() {
        print(() ->
            tomatoService.findAllFilterAny(
                tomato -> tomato.color() == Tomato.Color.RED,
                tomato -> tomato.size() == Tomato.Size.SMALL
            )
        );
    }

    private static void print(Supplier<List<Tomato>> action) {
        startPrint();
        action.get().forEach(System.out::println);
        endPrint();
    }

    private static void startPrint() {
        System.out.printf("%-10s %-14s %-13s %-10s", "", "COLOR", "SIZE", "USAGE");
        System.out.println("\n-----------------------------------------------------");
    }

    private static void endPrint() {
        System.out.println("-----------------------------------------------------\n");
    }
}