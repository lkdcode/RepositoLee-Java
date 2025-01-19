package task01;

class FinalExam {
    public void someMethod() {
        final String apple = "APPLE";
//        apple = "BANANA";
    }

    public static void primitive() {
        final int number = 3;
    /*
	    이 경우에도 재할당이 금지다. 원시값은 메모리 값 자체를 가지고 있지만
	    재할당 할 수 없으므로 해당 메서드 바디에서만 재할당 금지이다.
	    이 경우 역시 여전히 불변과 혼동하면 안 된다.
	    number += 3;
	    number -= 3;
	    number = 13;
	    number /= 3;
	    number *= 3;
	*/
    }

    public static int sum(int a) {
        a += 3;
        a += 10;
        a += 50;
        return a + 5;
    }

    public static void main(String[] args) {
        final int target = 100;
        int result = sum(target);

        System.out.println(result);
    }
}
