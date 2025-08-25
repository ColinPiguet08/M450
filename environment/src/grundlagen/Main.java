package grundlagen;

public class Main {
    public static void main(String[] args) {
        boolean allTestsPassed = PriceCalculatorTest.test_calculate_price();

        if (allTestsPassed) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed!");
        }
    }
}
