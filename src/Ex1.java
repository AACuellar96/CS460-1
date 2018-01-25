import java.util.Scanner;
public class Ex1 {
    public static void main(String[] args){
        System.out.println("Please enter the phrase you would like to be encrypt or decrypt.");
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine().toLowerCase().replaceAll("\\s","");
        System.out.println("Please enter the corresponding number to whether you would like to encrypt or decrypt.");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int crypt = scan.nextInt();
        System.out.println("Please enter the corresponding number to the cipher you would like to use.");
        System.out.println("1. Caesar Cipher");
        System.out.println("2. FairPlay Cipher");
        System.out.println("3. Vigenere Cipher");
        int cipher = scan.nextInt();
        switch(cipher){
            case 1: {
                System.out.println("Please enter the shift the cipher is using, from a range of 0-26");
                int shift = scan.nextInt();
                if(crypt==1){
                    System.out.println(CaesarCipher.encrypt(text,shift));
                }
                else{
                    System.out.println(CaesarCipher.decrypt(text,shift));
                }
                break;
            }
            case 2: {
                System.out.println("Please enter the keyword for the cipher.");
                scan.nextLine();
                String keyword = scan.nextLine().toLowerCase().replaceAll("\\s","");
                if(crypt==1){
                    System.out.println(FairPlayCipher.encrypt(text,keyword));
                }
                else{
                    System.out.println(FairPlayCipher.decrypt(text,keyword));
                }
                break;
            }
            case 3: {
                System.out.println("Please enter the keyword for the cipher.");
                scan.nextLine();
                String keyword = scan.nextLine().toLowerCase().replaceAll("\\s","");
                if(crypt==1){
                    System.out.println(VigenereCipher.encrypt(text,keyword));
                }
                else{
                    System.out.println(VigenereCipher.decrypt(text,keyword));
                }
                break;
            }
            default:
                break;
        }
    }
}
