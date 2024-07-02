package task12;

public class Divider implements Operation {
    @Override
    public double getResult(double a, double b) {
        return a / b;
    }

    @Override
    public String toString() {
        return "деление";
    }
}
