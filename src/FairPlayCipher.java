public class FairPlayCipher {

    private static char[][] matrix;
    private static int[] rowPlace;
    private static int[] colPlace;

    public static String encrypt(String plainText,String keyword){
        plainText=formatString(plainText);
        keyword.replaceAll("j","i");
        fillMatrix(keyword);
        String encryptedText="";
        for(int i=0;i<plainText.length();i+=2){
            int valA = plainText.charAt(i)-97;
            int valB = plainText.charAt(i+1)-97;
            if(rowPlace[valA]==rowPlace[valB]){
                if(colPlace[valA]!=4)
                    encryptedText+=matrix[rowPlace[valA]][colPlace[valA]+1];
                else
                    encryptedText+=matrix[rowPlace[valA]][0];
                if(colPlace[valB]!=4)
                    encryptedText+=matrix[rowPlace[valB]][colPlace[valB]+1];
                else
                    encryptedText+=matrix[rowPlace[valB]][0];
            }
            else if(colPlace[valA]==colPlace[valB]){
                if(rowPlace[valA]!=4)
                    encryptedText+=matrix[rowPlace[valA]+1][colPlace[valA]];
                else
                    encryptedText+=matrix[0][colPlace[valA]];
                if(rowPlace[valB]!=4)
                    encryptedText+=matrix[rowPlace[valB]+1][colPlace[valB]];
                else
                    encryptedText+=matrix[0][colPlace[valB]];
            }
            else{
                encryptedText+=matrix[rowPlace[valA]][colPlace[valB]];
                encryptedText+=matrix[rowPlace[valB]][colPlace[valA]];
            }
        }
        return encryptedText;
    }

    public static String decrypt(String encryptedText,String keyword){
        String plainText="";
        keyword.replaceAll("j","i");
        fillMatrix(keyword);
        for(int i=0;i<encryptedText.length();i+=2){
            int valA = encryptedText.charAt(i)-97;
            int valB = encryptedText.charAt(i+1)-97;
            if(rowPlace[valA]==rowPlace[valB]){
                if(colPlace[valA]!=0)
                    plainText+=matrix[rowPlace[valA]][colPlace[valA]-1];
                else
                    plainText+=matrix[rowPlace[valA]][4];
                if(colPlace[valB]!=0)
                    plainText+=matrix[rowPlace[valB]][colPlace[valB]-1];
                else
                    plainText+=matrix[rowPlace[valB]][4];
            }
            else if(colPlace[valA]==colPlace[valB]){
                if(rowPlace[valA]!=0)
                    plainText+=matrix[rowPlace[valA]-1][colPlace[valA]];
                else
                    plainText+=matrix[4][colPlace[valA]];
                if(rowPlace[valB]!=0)
                    plainText+=matrix[rowPlace[valB]-1][colPlace[valB]];
                else
                    plainText+=matrix[4][colPlace[valB]];
            }
            else{
                plainText+=matrix[rowPlace[valA]][colPlace[valB]];
                plainText+=matrix[rowPlace[valB]][colPlace[valA]];
            }
        }
        return plainText;
    }

    private static String formatString(String text){
        text.replaceAll("j","i");
        for(int i=0;i<text.length();i+=2){
            if(i!=text.length()-1&&text.charAt(i)==text.charAt(i+1))
                text = new StringBuilder(text).insert(i+1,'x').toString();
        }
        if(text.length()%2==1)
            text+='x';
        return text;
    }
    private static void fillMatrix(String keyword){
        matrix = new char[5][5];
        rowPlace= new int[26];
        colPlace= new int[26];
        int placeK=0;
        int placeA=0;

        for(int row=0;row<5;row++){
            for(int col=0;col<5;col++){
                if(placeK<keyword.length()){
                    while(true) {
                        char val = keyword.charAt(placeK);
                        placeK++;
                        if(row==0&&col==0){
                            matrix[0][0]=val;
                            break;
                        }
                        else if(rowPlace[val-97]==0 && colPlace[val-97]==0){
                            matrix[row][col]=val;
                            rowPlace[val-97]=row;
                            colPlace[val-97]=col;
                            break;
                        }
                        else if(placeK>=keyword.length())
                            break;
                    }
                }
                else{
                    while(true) {
                        char val = (char) (97 + placeA);
                        if(val=='j') {
                            val++;
                            placeA++;
                        }
                        placeA++;
                        if(rowPlace[val-97]==0 && colPlace[val-97]==0 && val!=keyword.charAt(0)){
                            matrix[row][col]=val;
                            rowPlace[val-97]=row;
                            colPlace[val-97]=col;
                            break;
                        }
                        else if(placeA>=26)
                            break;
                    }
                }
            }
        }
    }
}
