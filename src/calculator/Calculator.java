package calculator;

public final class Calculator {
    public double divide(double numerator, double denominator) {
        if (Double.isNaN(numerator) || Double.isNaN(denominator)) {
            throw new IllegalArgumentException("NaN is not supported");
        }

        if (denominator == 0.0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        return numerator / denominator;
    }
}
