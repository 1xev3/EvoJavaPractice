package task12;

public class Subtractor implements Operation {
    @Override
    public double getResult(double a, double b) {
        return a - b;
    }

    @Override
    public String toString() {
        return "вычитание";
    }
}
