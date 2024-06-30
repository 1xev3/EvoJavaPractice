import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter your name: ");

        String name = in.nextLine();
        System.out.printf("Your name: %s \n", name);

        in.close();
    }
}
