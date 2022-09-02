/* This class is used to represent a player, and to keep track of their wins and losses. */

public class Player {
    // Add instance variables here.
    String Name;
    int NumWin;
    int NumLose;

    // Complete these constructors.
    public Player() {
        NumWin = 0;
        NumLose = 0;
    }
    public Player(String n) {
        Name = n;
        NumWin = 0;
        NumLose = 0;
    }
    public Player(String namen, int w, int l) {
        Name = namen;
        NumWin = w;
        NumLose = l;
    }

    // add accessors and mutators here.
    public String getName() {
        return Name;
    }
    public void setName(String Name_set) {
        Name = Name_set;
    }

    public int getNumWin() {
        return NumWin;
    }
    public void setNumWin(int NumWin_set) {
        NumWin = NumWin_set;
    }

    public int getNumLose() {
        return NumLose;
    }
    public void setNumLose(int NumLose_set) {
        NumLose = NumLose_set;
    }

    // Complete the display() method.
    public void display() {
        System.out.println("Player Name:\0" + Name + "\n");
        System.out.println("Number of Wins:\0" + NumWin +"\n");
        System.out.println("Number of Loses:\0" + NumLose + "\n");

    }
}
