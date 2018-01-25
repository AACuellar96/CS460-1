public class CaesarCipher {
    public static String encrypt(String plainText,int shift){
        String encryptedText="";
        for(int i=0;i<plainText.length();i++){
            char temp = (char) (plainText.charAt(i)+shift);
            if(temp>122)
                temp-=26;
            encryptedText+=temp;
        }
        return encryptedText;
    }

    public static String decrypt(String encryptedText,int shift){
        String plainText="";
        for(int i=0;i<encryptedText.length();i++){
            char temp = (char) (encryptedText.charAt(i)-shift);
            if(temp<97)
                temp+=26;
            plainText+=temp;
        }
        return plainText;
    }
}
