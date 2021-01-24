package lesson3;

import java.util.Random;
import java.util.Scanner;

public class ThirdApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void main(String[] args) {
        gameGuessNumber();
        gameGuessWord();
    }

    public static int getUserNumber() {
        int number;
        do {
            System.out.print("Enter a number from 1 to 9: ");
            number = sc.nextInt();
        } while (number < 1 || number > 9);
        return number;
    }

    public static int getCompNumber() {
        return rand.nextInt(9) + 1;
    }

    public static void gameGuessNumber() {
        int answer;
        int number, guess;
        String lessOrGreater;
        do {
            number = getCompNumber();
            System.out.println("I riddled the number. Try to guess it. You have 3 attempts.");
            for (int i = 1; i < 4; i++) {
                guess = getUserNumber();
                if (guess == number) {
                    System.out.println("Congratulations!!! You guessed my number!!! It is " + number + "!!!");
                    break;
                }
                else {
                    if (guess < number) lessOrGreater = "smaller";
                    else lessOrGreater = "bigger";
                    System.out.println("No. Your number is " + lessOrGreater + " than my riddled number.");
                    System.out.println("You have " + (3 - i) + " attempts left.");
                }
                if (i == 3) System.out.println("You have lost. You are a loser.");
            }
            System.out.print("Do you want to play again? (1 - yes, 0 - no): ");
            answer = sc.nextInt();
        } while (answer == 1);
    }

    private static void gameGuessWord() {
        String[] words = { "apple", "orange", "lemon", "banana", "apricot", "avocado",
                "broccoli", "carrot", "cherry", "garlic", "grape", "melon" };
        String word, guess;
        int answer;

        do {
            guess = "";
            word = getCompWord(words);
            System.out.println("I riddled the word. This is a fruit or a vegetable. Try to guess it " +
                    "(enter 'exit' to exit).");
            while (!guess.equals(word)) {
                guess = getUserWord();
                if (guess.equals("exit")) break;
                int i = 0;
                while (i < guess.length() && i < word.length()) {
                    if (guess.charAt(i) == word.charAt(i)) System.out.print(word.charAt(i));
                    else System.out.print("#");
                    i++;
                }
                while (!guess.equals(word) && i < 16) {
                    System.out.print("#");
                    i++;
                }
                System.out.println();
            }
            if (!guess.equals("exit")) {
                System.out.println("Congratulations!!! You guessed my word!!! It is '" + word + "'");
            }
            System.out.print("Do you want to play again? (1 - yes, 0 - no): ");
            answer = sc.nextInt();
        } while (answer == 1);


    }

    private static String getUserWord() {
        System.out.print("Enter your word: ");
        return sc.nextLine();
    }

    private static String getCompWord(String[] words) {
        return words[rand.nextInt(words.length)];
    }

}
