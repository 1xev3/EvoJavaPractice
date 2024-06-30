package task2;

import java.util.Scanner;
import java.lang.Math;

//- Считать потоком ввода три числа, введенных пользователем, и сохранить их в целочисленных переменных a, b и c
//- Вывести на консоль переменные, значения которых делятся на 5 (вида: a=25, c=15),
//  если нет ни одного значений, делящегося на 5, вывести строку: "нет значений, кратных 5"
//- Вывести на консоль значение от целочисленного деления a на b (результат - целое число)
//- Вывести на консоль значение от деления a на b (результат - число с плавающей запятой)
//- Вывести на консоль значение от деления a на b, округленного до ближайшего целого в большую сторону
//- Вывести на консоль значение от деления a на b, округленного до ближайшего целого в меньшую сторону
//- Вывести на консоль значение от деления a на b, округленного до ближайшего целого математическим округлением
//- Вывести на консоль остаток от деления b на c
//- Вывести на консоль наименьшее значение из a и b
//- Вывести на консоль наибольшее значение из b и c

public class Main {
    public static void main(String[] args) {
        int a,b,c;

        System.out.print("Enter three numbers:\na(1) = ");

        Scanner in = new Scanner(System.in);
        a = in.nextInt();

        System.out.print("b(2) = ");
        b = in.nextInt();

        System.out.print("c(3) = ");
        c = in.nextInt();
        in.close(); //we don`t need it anymore :)

        int[] numbers = {a, b, c};
        boolean isDivisibleBy5 = false;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 5 == 0) {
                System.out.printf("Number %d is dividing by 5 (%d = %d)\n", i+1, i+1, numbers[i]);
                isDivisibleBy5 = true;
            }
        }
        if (!isDivisibleBy5) {
            System.out.println("No numbers dividing by 5");
        }

        float result = (float)a/b;

        System.out.println("int a/b = " + (int)result);
        System.out.printf("float a/b = %f\n", result);
        System.out.println("ceil a/b = " + Math.ceil(result));
        System.out.println("floor a/b = " + Math.floor(result));
        System.out.println("round a/b = " + Math.round(result));
        System.out.println("b%c = " + b%c);
        System.out.println("min(a,b) = " + Math.min(a,b));
        System.out.println("max(b,c) = " + Math.max(b,c));
    }
}
