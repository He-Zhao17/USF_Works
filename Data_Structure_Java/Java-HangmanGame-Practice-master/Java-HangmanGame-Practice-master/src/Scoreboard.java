/* Scoreboard.java
    This class will be used to display to the user
    the correctly and incorrectly guessed letters, as well as the solution so far.
 */

public class Scoreboard {
    // Add instance variables here.
    String CorrectGuesses;
    String IncorrectGuesses;
    String Solution;

    // Complete both constructors.
    public Scoreboard() {
        CorrectGuesses = " ";
        IncorrectGuesses = " ";
        Solution = " ";

    }
    public Scoreboard(String initialGuesses, String initialSolution) {
        CorrectGuesses = initialGuesses;
        IncorrectGuesses = " ";
        Solution = initialSolution;
    }

    // Add accessors and mutators here. I've put in one to get you started.
    public String getCorrectGuesses() {
        return CorrectGuesses;
    }
    public void setCorrectGuesses(String CorrectGuesses_set) {
        CorrectGuesses = CorrectGuesses_set;
    }

    public String getIncorrectGuesses() {
        return IncorrectGuesses;
    }
    public void setIncorrectGuesses(String IncorrectGuesses_set) {
        IncorrectGuesses =IncorrectGuesses_set;
    }

    public String getSolution() {
        return Solution;
    }
    public void setSolution(String Solution_set) {
        Solution = Solution_set;
    }

    // Print out the scoreboard for the user.
    public void display() {
        System.out.println("The right letters you have guessed:\0" + CorrectGuesses + "\n");
        System.out.println("The wrong letters you have guessed:\0" + IncorrectGuesses + "\n");
        System.out.println("Now, the partial solution of the word:\0 " + Solution + "\n");
    }

}

