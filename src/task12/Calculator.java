package task12;

public class Calculator {
    Operation operation;
    public Calculator(Operation operation) {
        this.operation = operation;
    }
    public void calc(double a, double b) {
        System.out.println("Операция - " + operation.toString() + ". Результат: " + operation.getResult(a, b));
    }
}
