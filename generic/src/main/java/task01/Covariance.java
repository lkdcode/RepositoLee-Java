package task01;

import java.util.List;

class Covariance {
    public static void main(String[] args) {
        List<Cat> cats = List.of(new Cat(), new Cat(), new Cat());
        List<? extends Animal> animals = cats;

//        animals.add(new Cat()); // ❌ 쓰기만 허용
    }
}
