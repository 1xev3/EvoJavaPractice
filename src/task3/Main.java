package task3;

import java.util.Random;

import java.util.Map;
import java.util.HashMap;

//💡- Заполнить массив из 20 элементов случайными целыми значениями в диапазоне от 1 до 15 включительно
//💡- Вывести на экран содержимое массива
//💡- Вывести на экран значения, которые встречаются в массиве больше одного раза, в формате, вида:

//Пример:
//Вывод: [1, 2, 3, 4, 5, 6, 7, 8, 5, 10, 20, 12, 20, 14, 15, 16, 17, 18, 19, 20]
//Вывод: Число '5' встречается 2 раза
//Вывод: Число '20' встречается 3 раза

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();

        Map<Integer, Integer> map = new HashMap<>();

        int[] mass = new int[15];

        // Зачем нам > 1 цикла, если можно сделать все в одном?
        System.out.print("[");
        for (int i = 0; i < mass.length; i++) {
            int val = rnd.nextInt(0,15);
            mass[i] = val;
            System.out.print(val + ((i == mass.length - 1) ? "" : ", "));

            map.merge(val, 1, Integer::sum);
        }
        System.out.println("]");

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.printf("Число '%s' встречается %d раз(а)\n", entry.getKey(), entry.getValue());
            }
        }
    }
}
