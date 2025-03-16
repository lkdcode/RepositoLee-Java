package task04;

import lombok.val;

public class LombokVal {
    public static void main(String[] args) {
        val();
    }

    public static void val() {
        val str1 = "Hello, ";
        val str2 = "val!";

        /*
         str1 = "update string...";
         str2 = "update string...";
         */

        System.out.println(str1 + str2);
    }
}