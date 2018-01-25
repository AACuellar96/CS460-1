import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LetterFreqAtt {
    private static Double[] freqV = {8.167,1.492,2.782,4.253,12.702,2.228,2.015,6.094,6.996,0.153,0.772,4.025,2.406,6.749,7.507,1.929,0.095,5.987,6.327,9.056,2.758,0.978,2.360,0.150,1.974,0.074};
    private static Character[] freqL = {'e','t','a','o','i','n','s','h','r','d','l','c','u', 'm','w','f','g','y','p','b','v','k','j','x','q','z'};

    public static void lFAttack(String encryptedText,int possibilities) {
        Letters[] textFreq = new Letters[26];
        for (int i = 0; i < 26; i++) {
            textFreq[i] = new Letters((char) (97 + i), 0);
        }

        //Setting frequencies
        Arrays.sort(freqV, Collections.reverseOrder());
        for (int i = 0; i < encryptedText.length(); i++) {
            if (encryptedText.charAt(i) > 96 && encryptedText.charAt(i) < 123)
                textFreq[encryptedText.charAt(i) - 97].freq++;
        }
        for (int i = 0; i < 26; i++) {
            textFreq[i].freq /= encryptedText.length();
            textFreq[i].freq *= 100;
        }
        Arrays.sort(textFreq, Collections.reverseOrder());
        // A list of locations where the letter appears in the cipher
        List<List<Integer>> occurrences = new ArrayList<List<Integer>>(0);
        for (int i = 0; i < 26; i++) {
            List<Integer> let = new ArrayList<>();
            occurrences.add(let);
        }
        for (int letter = 0; letter < 26; letter++) {
            char currLetter = (char) (97 + letter);
            int index = encryptedText.indexOf(currLetter);
            while (index >= 0) {
                occurrences.get(letter).add(index);
                index = encryptedText.indexOf(currLetter, (index + 1));
            }
        }

        //Used to replace chars
        StringBuilder plainText = new StringBuilder(encryptedText);
        //For the two most common
        boolean[] taken = new boolean[26];
        int mostComm = textFreq[0].getLetter() - 97;
        int secMostComm = textFreq[1].getLetter() - 97;
        for (int i = 0; i < occurrences.get(mostComm).size(); i++) {
            int index = occurrences.get(mostComm).get(i);
            plainText.setCharAt(index, 'e');
        }
        for (int i = 0; i < occurrences.get(secMostComm).size(); i++) {
            int index = occurrences.get(secMostComm).get(i);
            plainText.setCharAt(index, 't');
        }
        taken[4] = true;
        taken[19] = true;


        for (int poss = 0; poss < possibilities; poss++) {
            // For every possibility after the first it will make a switch of the placement of two letters
            // in the textFreq array as long as theyre within 10% of each other. One switch per poss.
            if(poss>0){
                for(int j=2+poss;j<26;j++){
                    double val = textFreq[j].freq;
                    boolean switchV=false;
                    for(int k=j+1;k<26;k++){
                        double val2 = textFreq[k].freq;
                        if((val*0.9)<val2 && val2<(val*1.1)){
                                Letters temp = textFreq[j];
                                textFreq[j] = textFreq[k];
                                textFreq[k] = temp;
                                switchV = true;
                                break;
                        }
                    }
                    if(switchV)
                        break;
                }
            }
            // Looks for a similar frequency within initally 5% range, if that fails expands by 5%
            // and if that fails another 5% and so on until it finds a letter. Then replaces it in the text.
            for (int i = 2; i < 26; i++) {
                double mult = 0;
                if (textFreq[i].freq != 0) {
                    double max = textFreq[i].freq * (1.05);
                    double min = textFreq[i].freq * (0.95);
                    int place = 0;
                    double freq = freqV[place];
                    while (true) {
                        if (freq > max || freq < min || taken[freqL[place] - 97]) {
                            place++;
                            if (place > 25) {
                                place = 0;
                                mult++;
                                max = textFreq[i].freq * (1.05 + (0.05 * (mult+poss)));
                                min = textFreq[i].freq * (0.95 - (0.05 * (mult+poss)));
                            }
                            freq = freqV[place];
                            continue;
                        } else {
                            char realV = freqL[place];
                            for (int j = 0; j < occurrences.get(textFreq[i].getLetter() - 97).size(); j++) {
                                int index = occurrences.get(textFreq[i].getLetter() - 97).get(j);
                                plainText.setCharAt(index, realV);
                            }
                            taken[freqL[place] - 97] = true;
                            break;
                        }
                    }
                } else
                    break;
            }

            System.out.println((poss+1) +"."+plainText.toString());
            for(int i=0;i<26;i++)
                if(i!=4 && i!=19)
                    taken[i]=false;
        }
    }
}