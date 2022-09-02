public class Player {
    private String name;
    private int wins;
    private int losses;

    public Player() {
        name = "";
        wins = 0;
        losses = 0;
    }

    public Player(String n) {
        name = n;
    }

    public Player(String n, int w, int l) {
        name = n;
        wins = w;
        losses = l;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int l) {
        losses = l;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int w) {
        wins = w;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public Player copy() {
        Player otherPlayer = new Player();
        otherPlayer.setName(name);
        otherPlayer.setWins(wins);
        otherPlayer.setLosses(losses);
        return otherPlayer;
    }

    public void display() {
        System.out.println("Name: " + name + " Wins: " + wins + " Losses: " + losses);
    }
}
