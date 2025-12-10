package apps;

public class Golfer implements Comparable<Golfer> {

    protected String name;
    protected int score;

    public Golfer(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int compareTo(Golfer other) {
        if (this.score < other.score)
            return -1;
        else if (this.score > other.score)
            return +1;
        else
            return this.name.compareTo(other.name);
    }

    public String toString() {
        return (score + ": " + name);
    }
}
