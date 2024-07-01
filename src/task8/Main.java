package task8;

import java.util.*;

//💡- Создать класс "User", содержащий две переменных:
//
//private String name;
//
//private Integer age;
//
//💡- Добавить в класс геттеры и сеттеры, присваивающие и возвращающие значения этих переменных
//
//💡- Добавить в класс метод: public String toString(), который возвращает строку вида: "Вася, возраст 25 лет",
// где "Вася" - значение переменной name, а 25 - значение переменной age.
//
//💡- Создать конструктор класса, принимающий на вход два значения и инициализирующий ими эти переменные
//
//в методе main главного класса:
//
//💡- Создать новую мапу HashMap<Integer, List<User>>(), которая будет в качестве ключа хранить возраст,
// а в качестве значения - список пользователей;
//
//💡- Циклически запросить у оператора ввести данные 5-ти пользователей и записать объекты, созданные при
// вводе, в HashMap. Причем, если ключ (возраст) в мапе уже существует, то добавлять объект очередного
// пользователя в список, лежащий в значении по ключу. Если ключ в мапе еще не существует, то создавать
// новый список, записывать туда юзера и класть этот список в мапу по этому ключу.
//
//💡- Запросить у пользователя ввести требуемый возраст и, если такой ключ есть в мапе, вывести на консоль
// пользователей из списка, полученного по ключу из мапы, отсортированных по имени.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name;
        int age; // why not to be primitive?

        HashMap<Integer, List<User>> userAgeMap = new HashMap<Integer, List<User>>(5);

        for (int i = 1; i <= 5; i++) {
            System.out.printf("Enter %d class name:\n> ", i);
            name = in.nextLine();
            System.out.printf("Enter %d class age:\n> ", i);
            age = in.nextInt();
            in.nextLine(); // remove \n after age

            if (!userAgeMap.containsKey(age)) {
                userAgeMap.put(age, new ArrayList<User>());
            }
            userAgeMap.get(age).add(new User(name, age));
        }

        System.out.print("Enter age to find:\n> ");
        age = in.nextInt();

        if (userAgeMap.containsKey(age)) {
            for (User usr : userAgeMap.get(age)) {
                System.out.println(usr);
            }
        } else {
            System.out.println("No users with this age");
        }

        //OUTPUT:
        //User Dmitry, age: 25
        //User Alexander, age: 25
        //User Sasha, age: 25
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