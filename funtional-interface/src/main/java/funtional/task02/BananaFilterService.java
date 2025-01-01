package funtional.task02;

import funtional.Banana;
import funtional.Color;
import funtional.Weight;

import java.util.List;

class BananaFilterService {

    public List<Banana> filterRedBananas(List<Banana> list) {
        return list.stream()
            .filter(banana -> banana.color().equals(Color.RED))
            .toList();
    }

    public List<Banana> filterGreenBananas(List<Banana> list) {
        return list.stream()
            .filter(banana -> banana.color().equals(Color.GREEN))
            .toList();
    }

    public List<Banana> filterHeavyBanana(List<Banana> list) {
        return list.stream()
            .filter(banana -> banana.weight().equals(Weight.HEAVY))
            .toList();
    }

    public List<Banana> filterLightBananas(List<Banana> list) {
        return list.stream()
            .filter(banana -> banana.weight().equals(Weight.LIGHT))
            .toList();
    }
}