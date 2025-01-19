package task03;

import java.util.Arrays;
import java.util.List;

class Immutable {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
//        list.add(6);

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        list2.set(0, 50);
        System.out.println(list2);
        list2.add(50);
    }
}
