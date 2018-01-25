import java.util.Scanner;
public class Ex2 {
    public static void main(String[] args) {
        System.out.println("Please enter the text to decrypt.");
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine().toLowerCase();
        LetterFreqAtt.lFAttack(text,5);
    }
}
