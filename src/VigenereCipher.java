public class VigenereCipher {
    public static String encrypt(String plainText,String keyword){
        String encryptedText="";
        for(int i=0;i<plainText.length();i++){
            char temp = (char) (plainText.charAt(i)+(keyword.charAt( i % keyword.length())-97));
            if(temp>122)
                temp-=26;
            encryptedText+=temp;
        }
        return encryptedText;
    }

    public static String decrypt(String encryptedText,String keyword){
        String plainText="";
        for(int i=0;i<encryptedText.length();i++){
            char temp = (char) (encryptedText.charAt(i)-(keyword.charAt(i % keyword.length())-97));
            if(temp<97)
                temp+=26;
            plainText+=temp;
        }
        return plainText;
    }
}
