package task10;

import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        int a = 10000000;
        int b = 1000000000;

        long sum = LongStream.range(a, b).sum();
        System.out.println("Сумма чисел между " + a + " и " + b + ": " + sum);
    }
}

