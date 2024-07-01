package task6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//💡💡- Создать класс "User", содержащий две переменных:
//
//private String name;
//
//private Integer age;
//
//💡- Добавить в класс геттеры и сеттеры, присваивающие и возвращающие значения этих переменных
//
//💡- Добавить в класс метод: public String toString(), который возвращает строку вида: "Вася, возраст 25 лет", где "Вася" - значение переменной name, а 25 - значение переменной age.
//
//💡- Создать конструктор класса, принимающий на вход два значения и инициализирующий ими эти переменные
//
//в методе main главного класса:
//
//💡- Последовательно запросить у пользователя ввести строку с именем, затем число возраста, сохранить их в соответствующих переменных
//
//💡- Создать объект класса User, передав в его конструктор эти две переменных в качестве значений, которые он может принять
//
//💡- Еще раз запросить у оператора имя и возраст другого юзера
//
//💡- Создать второй объект класс User, передав в его конструктор эти две переменных в качестве значений, которые он может принять
//
//💡- Вывести в консоль значение, которое возвращает toString() у объекта с наименьшим age


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name;
        int age; // why not to be primitive?

        System.out.print("Enter first class name:\n> ");
        name = in.nextLine();
        System.out.print("Enter first class age:\n> ");
        age = in.nextInt();
        in.nextLine(); // remove \n after age

        User firstUser = new User(name, age);

        System.out.print("Enter second class name:\n> ");
        name = in.nextLine();
        System.out.print("Enter second class age:\n> ");
        age = in.nextInt();

        User secondUser = new User(name, age);

        int age1 = firstUser.GetAge();
        int age2 = secondUser.GetAge();
        if (age1 > age2) {
            System.out.println(firstUser);
        } else if (age1 < age2) {
            System.out.println(secondUser);
        }
    }
}

class User {
    private String name;
    private Integer age;

    User() {
        this.name = "N/A";
        this.age = 0;
    }

    User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetName() {
        return this.name;
    }

    public void SetAge(Integer age) {
        this.age = age;
    }

    public Integer GetAge() {
        return this.age;
    }

    public String toString() {
        return "User " + this.name + ", age: " + this.age;
    }
}