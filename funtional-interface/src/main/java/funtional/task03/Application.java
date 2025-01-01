package funtional.task03;

import funtional.Banana;
import funtional.BananaList;
import funtional.Color;
import funtional.Weight;

import java.util.List;

class Application {
    public static void main(String[] args) {
        BananaFilterService bananaFilterService = new BananaFilterService();
        List<Banana> list = BananaList.LIST;

        List<Banana> redBananas = bananaFilterService.filteredBananas(list, banana -> banana.color().equals(Color.RED));
        System.out.println("redBananas = " + redBananas);
        List<Banana> greenBananas = bananaFilterService.filteredBananas(list, banana -> banana.color().equals(Color.GREEN));
        System.out.println("greenBananas = " + greenBananas);
        List<Banana> heavyBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.HEAVY));
        System.out.println("heavyBananas = " + heavyBananas);
        List<Banana> lightBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.LIGHT));
        System.out.println("lightBananas = " + lightBananas);

        List<Banana> lightAndRedBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.LIGHT) && banana.color().equals(Color.RED));
        System.out.println("lightAndRedBananas = " + lightAndRedBananas);
        List<Banana> heavyAndRedBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.HEAVY) && banana.color().equals(Color.RED));
        System.out.println("heavyAndRedBananas = " + heavyAndRedBananas);
        List<Banana> lightAndGreenBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.LIGHT) && banana.color().equals(Color.GREEN));
        System.out.println("lightAndGreenBananas = " + lightAndGreenBananas);
        List<Banana> heavyAndGreenBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.HEAVY) && banana.color().equals(Color.GREEN));
        System.out.println("heavyAndGreenBananas = " + heavyAndGreenBananas);
    }
}