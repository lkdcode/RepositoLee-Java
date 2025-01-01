package funtional.task01;

import funtional.Banana;
import funtional.Color;
import funtional.Weight;

import java.util.ArrayList;
import java.util.List;

class BananaFilterService {
    public List<Banana> filterRedBananas(List<Banana> list) {
        List<Banana> result = new ArrayList<>();

        for (Banana banana : list) {
            if (banana.color().equals(Color.RED)) {
                result.add(banana);
            }
        }

        return result;
    }

    public List<Banana> filterGreenBananas(List<Banana> list) {
        List<Banana> result = new ArrayList<>();

        for (Banana banana : list) {
            if (banana.color().equals(Color.GREEN)) {
                result.add(banana);
            }
        }

        return result;
    }

    public List<Banana> filterHeavyBanana(List<Banana> list) {
        List<Banana> result = new ArrayList<>();

        for (Banana banana : list) {
            if (banana.weight().equals(Weight.HEAVY)) {
                result.add(banana);
            }
        }

        return result;
    }

    public List<Banana> filterLightBananas(List<Banana> list) {
        List<Banana> result = new ArrayList<>();

        for (Banana banana : list) {
            if (banana.weight().equals(Weight.LIGHT)) {
                result.add(banana);
            }
        }

        return result;
    }
}