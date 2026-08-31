import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String playAgain;
        int round = 0;

        do
        {
            round++;

            // Generate a new number for every round
            int number = random.nextInt(100) + 1;

            int attempts = 0;
            int maxAttempts = 7;
            int guess = 0;

            System.out.println("\n==============================");
            System.out.println("         ROUND " + round);
            System.out.println("==============================");

            do
            {
                System.out.print("Enter your guess (1-100): ");
                guess = sc.nextInt();

                // Basic input validation
                if (guess < 1 || guess > 100)
                {
                    System.out.println("Please enter a number between 1 and 100.");
                    continue;
                }

                attempts++;

                System.out.println("Your Guess: " + guess);
                System.out.println("Attempts: " + attempts + "/" + maxAttempts);

                if (guess > number)
                {
                    System.out.println("Too High!");
                }
                else if (guess < number)
                {
                    System.out.println("Too Low!");
                }
                else
                {
                    System.out.println("Correct! 🎉");
                    System.out.println(
                        "Round " + round +
                        " — guessed in " + attempts + " attempts."
                    );
                }

            } while (attempts < maxAttempts && guess != number);

            // Player loses after maximum attempts
            if (guess != number)
            {
                System.out.println("\nYou Lost!");
                System.out.println("The number was: " + number);

                System.out.println(
                    "Round " + round +
                    " — not guessed within " + maxAttempts + " attempts."
                );
            }

            // Play Again
            System.out.print("\nPlay Again? (yes/no): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("\n==============================");
        System.out.println("       GAME OVER");
        System.out.println("==============================");
        System.out.println("Total Rounds Played: " + round);
        System.out.println("Thank you for playing!");

        sc.close();
    }
}