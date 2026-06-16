import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(10) + 1;
        int chances = 3;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 10.");
        System.out.println("You have 3 chances to guess the correct number.");

        while (chances > 0) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed the correct number.");
                break;
            } else if (guess < secretNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }

            chances--;
            System.out.println("Chances left: " + chances);
        }

        if (chances == 0) {
            System.out.println("Game Over!");
            System.out.println("The correct number was: " + secretNumber);
        }

        scanner.close();
    }
}
