package grundlagen;

public class PriceCalculatorTest {

    public static boolean test_calculate_price() {
        PriceCalculator calc = new PriceCalculator();
        boolean test_ok = true;

        // Testfall 1: Kein Rabatt, nur Grundpreis
        double price1 = calc.calculatePrice(10000, 0, 0, 0, 0);
        if (price1 != 10000) test_ok = false;

        // Testfall 2: Grundpreis mit 10% Händlerrabatt
        double price2 = calc.calculatePrice(10000, 0, 0, 0, 10);
        if (price2 != 9000) test_ok = false;

        // Testfall 3: 3 Extras → 10% Rabatt auf Extras
        double price3 = calc.calculatePrice(20000, 1000, 3000, 3, 0);
        // Erwartet: 20000 + 1000 + 2700 = 23700
        if (price3 != 23700) test_ok = false;

        // Testfall 4: 5 Extras → 15% Rabatt auf Extras
        double price4 = calc.calculatePrice(20000, 1000, 3000, 5, 0);
        // Erwartet: 20000 + 1000 + 2550 = 23550
        if (price4 != 23550) test_ok = false;

        return test_ok;
    }

}
