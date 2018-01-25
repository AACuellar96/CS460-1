public class Letters implements Comparable{
    private char letter;
    public double freq;
    public Letters(char letter, double freq){
        this.letter=letter;
        this.freq=freq;
    }

    public char getLetter() {
        return letter;
    }

    public int compareTo(Object otherLetters){
        if(this.freq<((Letters) otherLetters).freq)
            return -1;
        else if(this.freq>((Letters) otherLetters).freq)
            return 1;
        else
            return 0;
    }

}
