package task02;

import java.util.ArrayList;
import java.util.List;

class FinalList {
    private static final List<Integer> LIST = new ArrayList<>();

    public void add(final int number) {
        LIST.add(number);
    }

    public void print() {
        LIST.forEach(System.out::println);
    }

    public static void main(String[] args) {
        final FinalList finalList = new FinalList();
        finalList.add(1);
        finalList.add(2);
        finalList.add(3);
        finalList.add(4);
        finalList.add(5);
        finalList.print();
    }
}