public class Scoreboard {
    private LinkedList<Character> correctGuesses;
    private LinkedList<Character> incorrectGuesses;
    private LinkedList<Character> partialSolution;

    public Scoreboard() {
        correctGuesses = new LinkedList<Character>();
        incorrectGuesses = new LinkedList<Character>();
        partialSolution = new LinkedList<Character>();
    }
    public Scoreboard(String initialGuesses, String initialSolution) {
        correctGuesses= new LinkedList<Character>();
        partialSolution = new LinkedList<Character>();
        for (int i = 0; i < initialGuesses.length(); i++) {
            correctGuesses.enqueue(initialGuesses.charAt(i));
        }
        for (int i = 0; i < initialSolution.length(); i++) {
            partialSolution.enqueue(initialSolution.charAt(i));
        }
        incorrectGuesses = new LinkedList<Character>();
    }

    public LinkedList<Character> getCorrectGuesses() {
        return correctGuesses;
    }

    public LinkedList<Character> getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public LinkedList<Character> getPartialSolution() {
        return partialSolution;
    }

    public void setCorrectGuesses(LinkedList<Character> guesses) {
        correctGuesses = guesses;
    }
    public void setIncorrectGuesses(LinkedList<Character> guesses) {
        incorrectGuesses = guesses;
    }
    public void setPartialSolution(LinkedList<Character> psol) {
        partialSolution = psol;
    }

    public void display() {
        System.out.println("Correct guesses: " + correctGuesses + " Incorrect Guesses: " + incorrectGuesses + " Solution so far: " + partialSolution);
    }


}

