package task01;

import java.util.ArrayList;
import java.util.List;

class Invariance {
    public static void main(String[] args) {
        List<Cat> cats = List.of(new Cat(), new Cat(), new Cat());
//        List<Animal> animals = cats; ❌
//        animals.add(dog); ❌ type safety

        List<Animal> animals = new ArrayList<>(cats); // ⭕️ upcasting
    }
}
