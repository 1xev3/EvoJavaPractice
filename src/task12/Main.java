package task12;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter first double:\n> ");
        double a = in.nextDouble();
        System.out.print("Enter second double:\n> ");
        double b = in.nextDouble();

        Operation[] operationList = {
                new Adder(),
                new Subtractor(),
                new Multiplier(),
                new Divider(),
        };

        for (Operation operation : operationList) {
            Calculator calculator = new Calculator(operation);
            calculator.calc(a, b);
        }
    }
}

