package task4;

import java.util.Scanner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

//4.1.
//
//💡- Ввести с консоли строку, сохранить ее в строковой переменной
//
//💡- Ввести с консоли подстроку, сохранить ее во второй строковой переменной
//
//💡- Подсчитать сколько раз подстрока встречается в строке и вывести это значение на экран.
//
//Пример:
//Вывод: Введите строку
//Ввод: мама, мамонт, матрас
//Вывод: Введите подстроку
//Ввод: ма
//Вывод: Подстрока 'ма' встречается 4 раза
//
//4.2.
//
//💡- Ввести строку
//
//💡- Заменить в строке все слова "кака" и "бяка" на "вырезано цензурой"
//
//Пример:
//Вывод: Введите строку
//Ввод: Это бяка? Нет это кака
//Вывод: Это вырезано цензурой? Нет это вырезано цензурой
//
//4.3.
//
//💡- Ввести строку с датой формата: 31.12.2020
//
//💡- Преобразовать строку даты в формат: 2020-12-31
//
//Пример:
//Вывод: Введите дату в формате 'дд.мм.гггг'
//Ввод: 31.12.2020
//Вывод: 2020-12-31
//
//4.4.
//
//💡- Сделать задание 4.3. с использованием классов Date и SimpleDateFormat и их соответствующих методов.

public class Main {
    private static void t1() {
        System.out.print("TASK 4.1\n");
        Scanner in = new Scanner(System.in);

        System.out.print("Enter string:\n> ");
        String str = in.nextLine();

        System.out.print("Enter substring:\n> ");
        String findStr = in.nextLine();

        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = str.indexOf(findStr, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }

        System.out.printf("Substring '%s' found %d times\n", findStr, count);
    }

    private static void t2() {
        System.out.print("TASK 4.2\n");
        Scanner in = new Scanner(System.in);

        System.out.print("Enter string:\n> ");
        String str = in.nextLine();

        str = str.replace("кака", "вырезано цензурой");
        str = str.replace("бяка", "вырезано цензурой");

        System.out.println(str);
    }

    private static void t3() {
        System.out.print("TASK 4.3\n");
        Scanner in = new Scanner(System.in);

        System.out.print("Enter date with format (DD.MM.YYYY):\n> ");
        String str = in.nextLine();

        String[] matches = str.split("\\.");

        for (int i = matches.length-1; i >= 0; i--) {
            System.out.print(matches[i]);
            if (i != 0) {
                System.out.print("-");
            }
        }

        System.out.print("\n");
    }

    private static void t4() {
        System.out.print("TASK 4.4\n");
        Scanner in = new Scanner(System.in);

        System.out.print("Enter date with format (DD.MM.YYYY):\n> ");
        String str = in.nextLine();

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = inputFormat.parse(str);
            String outputDate = outputFormat.format(date);
            System.out.println(outputDate);
        } catch (ParseException e) {
            System.out.println("Wrong date format");
        }
    }

    public static void main(String[] args) {
        t1();
        t2();
        t3();
        t4();
    }
}
