Lab 2: Hangman.

30 points. 3 points for each task. <br>
Rubric:
Complete, correct, conforming to style guides: 3/3 <br>
Mostly complete, some style errors 2/3 <br>
Missing major parts: 1/3 <br>
Incomplete or not working: 0/3 <br>

In this lab, you'll implement a text-based version of the game Hangman. This lab will give you some practice building classes, working with Strings, implementing methods, and doing some initial software design.

Style Requirements

Style your code using the [Google Java Style Guidelines](https://google.github.io/styleguide/javaguide.html).  

Document all of your methods, including parameter and return types, as well as a description of what the method does.

1. To begin, describe in English how the game should work. Put this in a comment at the top of HangmanGame.java. <br>
For example, <br>
The system guesses a word. <br>
The system presents the word with all letters hidden, and all letters guessed so far.<br>
While the number of incorrect guesses is less than six:<br>
(you fill in the rest)

Our program will consist of three classes - the Scoreboard, which displays the game progress, the Player, which represents the player, and the HangmanGame, which implements  the game play. (we'll sometimes call this the *engine*.)

2 - Next, create the Scoreboard class, as described in class. It should contain:
- instance variables for the correct guesses, incorrect guesses, and the partial solution.
- A default constructor and a constructor that allows us to set the partial guess and correct guesses
- Accessors and mutators for all instance variables.
- a display() method that prints it out.
- Add a main method that tests your scoreboard.

3- Next, create the Player class, as described in class. It should contain:
- instance variables for the player's name, number of wins, and number of losses.
- A default constructor and a constructor that allows us to set the player's name.
- Accessors and mutators for all instance variables.
- a display() method that prints it out.
- Extend scoreboard's main method to also test your Player.

4- Next, let's start to implement the game itself in the HangmanGame class.
It will need:
- A scoreboard
- a Player
- a String to store the secretWord
- a mask to keep track of the letters that have been revealed.
- To begin, we'll also need a static array of possible words.

Add these instance variables, along with a constructor that takes as an argument the number of guesses the player gets.

5 - Next, complete the checkGuess() method to return true if the guessed letter is in the word, and false otherwise.

6 - Next, complete the wordGuessed() helper method. It should use the mask to determine if all letters are guessed and return a boolean.

7 - Next, implement the playGame() method for a single game. It should use a while loop to:<br>
Prompt the user and input a guess <br>
Check to see whether the guess is correct <br>
Update the scoreboard, mask, and counters appropriately, and display the scoreboard.

Uncomment and extend the main method in HangmanGame to test this. (you will need to comment out the main in Scoreboard.)

8 - Now let's deal with incorrect user input. If the user repeats a guess, or puts in a guess that is not a letter, prompt them to re-enter their guess. You should use the checkInput() helper method to do this.

9 - Now let's allow the user to play multiple times. In your main method, create a Player, and ask the user for their name. Start a game, capture whether they won or lost, and ask them if they want to play again. Continue until they say "no."

10 - Last, let's extend the list of words we can use. Create a text file called "hangmanWords" and put some words in there. Your constructor should open this file, read in all the words, and choose one at random. (If the file is not present, use a default word.) 
    