public class PriceCalculator {

    public double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;
        double result;

        // Rabatt für Zusatzausstattungen
        if (extras >= 5) 
            addon_discount = 15;
        else if (extras >= 3)
            addon_discount = 10;
        else 
            addon_discount = 0;

        // Vergleich mit Händlerrabatt
        if (discount > addon_discount)
            addon_discount = discount;

        // Endpreis berechnen
        result = baseprice / 100.0 * (100 - discount) 
               + specialprice 
               + extraprice / 100.0 * (100 - addon_discount);

        return result;
    }
}
