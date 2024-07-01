package task7;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

//💡💡- Создать класс "User", содержащий две переменных:
//
//private String name;
//
//private Integer age;
//
//💡- Добавить в класс геттеры и сеттеры, присваивающие и возвращающие значения этих переменных
//
//💡- Добавить в класс метод: public String toString(), который возвращает строку вида: "Вася, возраст 25 лет", где "Вася" -
// значение переменной name, а 25 - значение переменной age.
//
//💡- Создать конструктор класса, принимающий на вход два значения и инициализирующий ими эти переменные
// в методе main главного класса:
//
//💡- Создать новый список ArrayList<User>();
//
//💡- Циклически запросить у оператора ввести данные 5-ти пользователей и записать объекты, созданные при вводе, в ArrayList<>
//
//💡- Вывести на консоль список пользователей (используя метод toString()), отсортированных по возрастанию возраста (сортировать,
// используя Collections.sort(), реализовав в нем Comparator, сравнивающий значения переменных age)


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name;
        int age; // why not to be primitive?

        List<User> userArrayList = new ArrayList<User>(5);

        for (int i = 1; i <= 5; i++) {
            System.out.printf("Enter %d class name:\n> ", i);
            name = in.nextLine();
            System.out.printf("Enter %d class age:\n> ", i);
            age = in.nextInt();
            in.nextLine(); // remove \n after age

            userArrayList.add(new User(name, age));
        }

        // we can use userArrayList.sort here
        Collections.sort(userArrayList, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                int age1 = lhs.GetAge();
                int age2 = rhs.GetAge();

                if (age1 > age2 ) {
                    return 1;
                }
                else if (age1 < age2) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });

        for (User userClass : userArrayList) {
            System.out.println(userClass);
        }
        //OUTPUT:
        //User Ss, age: 1
        //User Dmitry, age: 10
        //User Shun, age: 12
        //User Alexander, age: 51
        //User hello, age: 76
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