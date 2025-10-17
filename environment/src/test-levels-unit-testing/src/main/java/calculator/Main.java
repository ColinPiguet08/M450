package main.java.calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        double num1 = 2;
        double num2 = 2;

        double result = calculator.add(num1, num2);
        System.out.println("Result: " + result);
    }
}
