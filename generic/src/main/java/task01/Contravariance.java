package task01;

import java.util.List;

class Contravariance {
    public static void main(String[] args) {
        List<? super Cat> list = List.of(new Cat(), new Haru());
        list.forEach(e -> {
            Object e1 = e;
            System.out.println(e.getClass());
        });

    }
}
