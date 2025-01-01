package funtional.task04;

import funtional.Banana;
import funtional.BananaList;
import funtional.Color;
import funtional.Weight;

import java.util.List;
import java.util.function.Predicate;

public class Application {
    public static void main(String[] args) {
        BananaFilterService bananaFilterService = new BananaFilterService();
        List<Banana> list = BananaList.LIST;

        List<Banana> greenBananas = bananaFilterService.filteredBananas(list, Application::greenCondition);
        System.out.println("greenBananas = " + greenBananas);

        List<Banana> redBananas = bananaFilterService.filteredBananas(list, Application.redCondition());
        System.out.println("redBananas = " + redBananas);

        List<Banana> heavyAndGreenBananas = bananaFilterService.filteredBananas(list, Application.heavyAndGreenCondition());
        System.out.println("heavyAndGreenBananas = " + heavyAndGreenBananas);
    }

    private static boolean greenCondition(Banana banana) {
        return banana.color().equals(Color.GREEN);
    }

    private static Predicate<Banana> redCondition() {
        return banana -> banana.color().equals(Color.RED);
    }

    private static Predicate<Banana> heavyAndGreenCondition() {
        return banana -> banana.weight().equals(Weight.HEAVY) && banana.color().equals(Color.GREEN);
    }
}