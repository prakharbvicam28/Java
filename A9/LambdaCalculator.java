import java.util.Scanner;

// Functional Interface with a single abstract method
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class LambdaCalculator {

    public static void main(String[] args) {
        // 1. Lambda expression for Addition
        Calculator addition = (a, b) -> a + b;

        // 2. Lambda expression for Subtraction
        Calculator subtraction = (a, b) -> a - b;

        // 3. Lambda expression for Multiplication
        Calculator multiplication = (a, b) -> a * b;

        // 4. Lambda expression for Division (handles division by zero)
        Calculator division = (a, b) -> {
            if (b == 0) {
                System.out.println("Error: Division by zero is not allowed.");
                return 0;
            }
            return a / b;
        };

        // User Input
        Scanner sc = new Scanner(System.in);
        System.out.println("========== LAMBDA CALCULATOR ==========");
        System.out.print("Enter first number (a) : ");
        int a = sc.nextInt();
        System.out.print("Enter second number (b): ");
        int b = sc.nextInt();
        System.out.println("=======================================");

        // Executing operations via lambda expressions
        System.out.println("Addition       (" + a + " + " + b + ") = " + addition.calculate(a, b));
        System.out.println("Subtraction    (" + a + " - " + b + ") = " + subtraction.calculate(a, b));
        System.out.println("Multiplication (" + a + " * " + b + ") = " + multiplication.calculate(a, b));
        System.out.println("Division       (" + a + " / " + b + ") = " + division.calculate(a, b));
        System.out.println("=======================================");

        sc.close();
    }
}

