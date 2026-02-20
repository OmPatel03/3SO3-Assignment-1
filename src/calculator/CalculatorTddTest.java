package calculator;

public final class CalculatorTddTest {
    private CalculatorTddTest() {
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Iteration 1 tests (basic semantics)
        testDividePositiveNumbers(calculator);
        testDivideNegativeByPositive(calculator);

        // Iteration 2 tests (edge/robustness)
        testDivideByZeroThrows(calculator);
        testDivideNaNThrows(calculator);
        testDivideNegativeZeroThrows(calculator);

        System.out.println("All Calculator TDD tests passed.");
    }

    private static void testDividePositiveNumbers(Calculator calculator) {
        assertDoubleEquals(2.5, calculator.divide(5.0, 2.0), 1e-9,
                "Expected 5.0 / 2.0 to be 2.5");
    }

    private static void testDivideNegativeByPositive(Calculator calculator) {
        assertDoubleEquals(-3.0, calculator.divide(-9.0, 3.0), 1e-9,
                "Expected -9.0 / 3.0 to be -3.0");
    }

    private static void testDivideByZeroThrows(Calculator calculator) {
        assertThrows(ArithmeticException.class,
                () -> calculator.divide(10.0, 0.0),
                "Expected division by +0.0 to throw ArithmeticException");
    }

    private static void testDivideNaNThrows(Calculator calculator) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(Double.NaN, 2.0),
                "Expected NaN input to throw IllegalArgumentException");
    }

    private static void testDivideNegativeZeroThrows(Calculator calculator) {
        assertThrows(ArithmeticException.class,
                () -> calculator.divide(10.0, -0.0),
                "Expected division by -0.0 to throw ArithmeticException");
    }

    private static void assertDoubleEquals(double expected, double actual, double tolerance, String message) {
        if (Math.abs(expected - actual) > tolerance) {
            throw new AssertionError(message + ", got " + actual);
        }
    }

    private static void assertThrows(Class<? extends Throwable> expectedException,
                                     ThrowingRunnable action,
                                     String message) {
        try {
            action.run();
            throw new AssertionError(message + ", but no exception was thrown");
        } catch (Throwable t) {
            if (!expectedException.isInstance(t)) {
                throw new AssertionError(message + ", got " + t.getClass().getSimpleName());
            }
        }
    }

    @FunctionalInterface
    private interface ThrowingRunnable {
        void run();
    }
}
