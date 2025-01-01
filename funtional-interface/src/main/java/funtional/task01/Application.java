package funtional.task01;

import funtional.Banana;
import funtional.BananaList;

import java.util.List;

class Application {
    public static void main(String[] args) {
        BananaFilterService bananaFilterService = new BananaFilterService();
        List<Banana> list = BananaList.LIST;

        List<Banana> redBananas = bananaFilterService.filterRedBananas(list);
        System.out.println("redBananas = " + redBananas);

        List<Banana> greenBananas = bananaFilterService.filterGreenBananas(list);
        System.out.println("greenBananas = " + greenBananas);

        List<Banana> heavyBananas = bananaFilterService.filterHeavyBanana(list);
        System.out.println("heavyBananas = " + heavyBananas);

        List<Banana> lightBananas = bananaFilterService.filterLightBananas(list);
        System.out.println("lightBananas = " + lightBananas);
    }
}