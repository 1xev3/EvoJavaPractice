import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите любой номер: ");
        int num = in.nextInt();
        System.out.printf("Ваш номер: %d \n", num);
        in.close();
    }
}
