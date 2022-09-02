
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private Player gamePlayer;
    private Scoreboard sb;
    private String secretWord;
   // private static String[] words = {"java", "python", "computer", "keyboard"};
    private int[] mask;
    private int nGuesses;

    public HangmanGame(int n) {
        gamePlayer = new Player();
        String buf = "";

        nGuesses = n;

        File file = new File("./src/hangmanWords.txt");

        ArrayList<String> wordsList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String word = sc.nextLine();

                // Skip words that are not alphabetic
                if (!word.chars().allMatch(Character::isLetter)) {
                    continue;
                }
                wordsList.add(word);
            }
            sc.close();

        } catch(IOException e) {
            System.err.println("File not found or invalid, " + e.getMessage());
        }

        Random generator = new Random();
        int i = generator.nextInt(wordsList.size());
        mask = new int[wordsList.get(i).length()];
        secretWord = wordsList.get(i);
        for (int j = 0; j < secretWord.length(); j++) {
            buf += "*";
        }
        sb = new Scoreboard("", buf);

    }


    public boolean checkGuess(char guess) {
        boolean retValue = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess) {
                mask[i] = 1;
                retValue = true;
            }
        }
        return retValue;
    }

    public boolean wordGuessed() {
        for (int i = 0; i < mask.length; i++) {
            if (mask[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public void updatePartialSolution() {
        StringBuilder newSolution = new StringBuilder(secretWord);
        for (int i = 0; i < newSolution.length(); i++) {
            if (mask[i] == 0) {
                newSolution.setCharAt(i, '*');
            }
        }
        LinkedList<Character> temp = new LinkedList<Character>();
        sb.setPartialSolution(temp);
        for (int j = 0; j < newSolution.length(); j++) {
            sb.getPartialSolution().enqueue(newSolution.charAt(j));
        }
    }

    public boolean checkInput(String input) {
        if (input == null) {
            return false;
        }
        if (input.length() != 1) {
            return false;
        }
        char ch = input.charAt(0);
        if (!Character.isAlphabetic(ch)) {
            return false;
        }
        if (sb.getIncorrectGuesses().toString().contains(input)) {
            return false;
        }
        if (sb.getCorrectGuesses().toString().contains(input)) {
            return false;
        }
        return true;
    }



    public void playGame() {
        int guessCount = 0;
        Scanner sc = new Scanner(System.in);

        while (guessCount < nGuesses && !wordGuessed()) {
            System.out.print("Please enter your guess: ");
            String input = sc.nextLine();
            updatePartialSolution();

            if (!checkInput(input)) {
                System.out.println("Input is not valid. Please only enter one letter that you haven't guessed.");
                continue;
            }
            if (checkGuess(input.charAt(0))) {
                sb.getCorrectGuesses().enqueue(input.charAt(0));
                updatePartialSolution();
            } else {
                sb.getIncorrectGuesses().enqueue(input.charAt(0));
                guessCount++;
            }
            sb.display();

        }
        if (wordGuessed()) {
            System.out.println("Congratulations! You won with " + guessCount + " incorrect guesses.");
        } else {
            System.out.println("Too bad. The correct answer was " + secretWord);
        }

    }

    public static void main(String[] args) {
        HangmanGame hg = new HangmanGame(7);
        hg.playGame();

    }


}
