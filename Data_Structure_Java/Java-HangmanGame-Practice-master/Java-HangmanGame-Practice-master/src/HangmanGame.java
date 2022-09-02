/*
The system guesses a word.
The system presents the word with all letters hidden, and all letters guessed so far.
While the letter of incorrect guesses is less than deathtime: If you have guessed the word six times and still don’t know the word, you will die.
Every word of the word which has not been guessed is masked.
Every letter of the word which has been guessed is visible.
You can see the guessed letters and the wrong letters on the game interface.
Good luck!
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    // Add instance variables here.
    static Scoreboard Scoreboard_Now;
    static String SecretWord;
    static char[] Mask;
    static String[] WordArray;
    static int nGuesses;


    /*
    Complete this constructor. The input variable n refers to the number of incorrect guesses allowed.
    When you are ready to read in potential words from a file, you should do it inside the constructor.
     */
    public HangmanGame(int n) {
        nGuesses = n;
        // Get the words.
        ArrayList<String> Wordlist = new ArrayList<>();
        File hangmanWords = new File("hangmanWords.txt");
        if (!hangmanWords.exists()) {
            WordArray[0] = "default";
        } else {
            try {
                FileReader words_fr = new FileReader("hangmanWords.txt");
                BufferedReader words_bfr = new BufferedReader(words_fr);
                String word_str;
                while ((word_str = words_bfr.readLine()) != null) {
                    Wordlist.add(word_str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            int num_words = Wordlist.size();
            String[] wordarray = new String[num_words];
            for (int i = 0; i < num_words; i++) {
                String word_r = Wordlist.get(i);
                wordarray[i] = word_r;
            }
            WordArray = wordarray;
        }
        // Select the word randomly.
        Random random = new Random();
        int sel;
        sel = random.nextInt(WordArray.length);
        SecretWord = WordArray[sel];
        // Initial Mask.
        Mask = SecretWord.toCharArray();
        for (int j = 0; j < Mask.length; j++) {
            Mask[j] = '-';
        }
        // Initial Scoreboard_Now.
        Scoreboard_Now = new Scoreboard(" ",String.valueOf(Mask));
    }
    /* This method takes as input a character, and returns true if the guessed character is present in the secret word.
        It also updates the mask to show the positions of this character in the secret word as marked.
     */

    public boolean checkGuess(char guess) {
        for (int i = 0; i < Mask.length; i++) {
            if (Mask[i] == '-' && (SecretWord.toCharArray())[i] == guess) {
                Mask[i] = (SecretWord.toCharArray())[i];
                return true;
            } else {
            }
        }
        return false;
    }

    /* this helper method returns true if the word has been completely guessed.
    (i.e. all elements of the mask are 1.
     */

    public boolean wordGuessed() {
        for (int i = 0; i < Mask.length; i++) {
            if (Mask[i] == '-') {
                return false;
            }
        }
        return true;
    }

    /* this helper method will check to see if the input string is a
    letter that's already been guessed, or something other than a letter.
     */
    public boolean checkInput(String input) {
        if (input == null) {
            return false;
        } else {
            if (input.length() != 1) {
                return false;
            } else {
                if (Character.isLetter(input.charAt(0))) {
                    if (Scoreboard_Now.IncorrectGuesses == null) {
                        return true;
                    } else {
                        char[] cainc = Scoreboard_Now.IncorrectGuesses.toCharArray();
                        for (int i = 0; i < cainc.length; i++) {
                            if (input.charAt(0) == cainc[i]) {
                                return false;
                            } else {

                            }
                        }
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    // Complete playGame()

    public boolean playGame() {
        int guessCount = 0;
        Scoreboard_Now.display();
        while (guessCount < nGuesses && !wordGuessed()) {
            //prompt for input
            System.out.println("\nPlease input the letter you guess.\n");
            Scanner scan1 = new Scanner(System.in);
            String input = scan1.nextLine();
            // Check to see if it's correct.
            // Update the scoreboard, specifically the partialGuess and either the correct or incorrect letters.
            while (checkInput(input) == false) {
                System.out.println("Error. Please input the letter in the correct form.\n");
                Scanner scan2 = new Scanner(System.in);
                input = scan2.nextLine();
            }
            char guess = input.charAt(0);
            if (checkGuess(guess) == true) {
                if (Scoreboard_Now.CorrectGuesses == null) {
                    Scoreboard_Now.setCorrectGuesses(input);
                } else {
                    String scg = Scoreboard_Now.CorrectGuesses.concat(input);
                    Scoreboard_Now.setCorrectGuesses(scg);
                }
                Scoreboard_Now.setSolution(String.valueOf(Mask));
            } else {
                // if not, increment wrong guesses.
                if (Scoreboard_Now.IncorrectGuesses == null) {
                    Scoreboard_Now.setIncorrectGuesses(input);
                    guessCount++;
                } else {
                    String scig = Scoreboard_Now.IncorrectGuesses.concat(input);
                    Scoreboard_Now.setIncorrectGuesses(scig);
                    guessCount++;
                }
            }
            // Display the scoreboard.
            Scoreboard_Now.display();
        }
        // If they won, print "Congratulations." and return true.
        if (wordGuessed()) {
            System.out.println("Congratulations.");
            return true;
        } else {
            // If they lost, print "Too bad.", show the correct word, and return false.
            System.out.println("Too bad.");
            return false;
        }
    }

    //  Once you're ready to start testing the whole game, remove the main in Scoreboard.java and use this one.
    public static void main(String args[]) {
        // Prompt for the user's name.
        System.out.println("Please your name.\n");
        // Create a Player.
        Scanner scan3 = new Scanner(System.in);
        String nameinput = scan3.nextLine();
        Player Player_Now = new Player(nameinput);
        System.out.println("How many chances do you want?\n");
        Scanner scan4 = new Scanner(System.in);
        int n_guess = scan4.nextInt();
        System.out.println("Now begin the game.\n");
        HangmanGame newgame = new HangmanGame(n_guess);
        System.out.println(Mask);
        if(newgame.playGame() == true) {
            Player_Now.NumWin++;
        } else {
            Player_Now.NumLose++;
        }
        Player_Now.display();
        System.out.println("Do you want one more game?\nPlease input 1 or 2.\n1.Yes.\n2.No\n");
        Scanner scan5 = new Scanner(System.in);
        int yesno = scan5.nextInt();
        while (yesno == 1) {
            newgame = new HangmanGame(6);
            System.out.println(Mask);
            if(newgame.playGame() == true) {
                Player_Now.NumWin++;
            } else {
                Player_Now.NumLose++;
            }
            Player_Now.display();
            System.out.println("Do you want one more game?\nPlease input 1 or 2.\n1.Yes.\n2.No\n");
            scan5 = new Scanner(System.in);
            yesno = scan5.nextInt();
        }
        System.out.println("Game Over. Your scores is as follows.\n\n");
        Player_Now.display();


        // while not done:
        //      create a HangmanGame
        //      call playGame()
        //      increment the player's wins or losses
        // print their final score
    }

}
