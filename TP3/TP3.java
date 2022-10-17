package TP3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class TP3 {
    public static void main(String[] args){
        String filename = "TP3/ActualiteMinesDouaiCesar";
        double res = 0;
        String solVigenere = "";
        double[] frequenceAlphabetFrance = {0.0797, 0.0107, 0.0347, 0.04, 0.179, 0.0101,0.0104,0.0135,0.0734,0.003,0.00069,0.0548,0.0317, 0.0702,0.0527,0.028,0.0113,0.0664,0.0772,0.0728,0.0574,0.0117,0.00059,0.00449,0.003309,0.0004};


        //System.out.println(Cesar("Vous faites un TP d'informatique en ce moment", 5));
        //System.out.println(Vigenere("Vous faites un TP d'informatique en ce moment", "TPFI"));
        //writeFile(filename+"-e.txt", Vigenere(readFile(filename+".txt"), "TPFI"));

        String text = readFile(filename+".txt");
        String text2 = readFile("TP3/ActualiteMinesDOuaiVigenere.txt");
        //System.out.println(text);


        res = cesarFrequence(text);
        
        System.out.println(res);
        //System.out.println(Cesar(text, (int)res)); 
        
        solVigenere = solveVigenere(text2);
        System.out.println(solVigenere); 
    }

    public static char CesarChar(char c, int n){
        char res = c;
        if(c >= 'a' && c <= 'z'){
            res = (char)('a' + (c - 'a' + n)%26);
        }
        else if(c >= 'A' && c <= 'Z'){
            res = (char)('A' + (c - 'A' + n)%26);
        }
        return res;
    }

    public static String Cesar(String s, int n){
        String res = "";
        for(int i = 0; i < s.length(); i++){
            res += CesarChar(s.charAt(i), n);
        }
        return res;
    }

    public static String Vigenere(String s, String k){
        String res = "";
        int j =0;
        for(int i = 0; i < s.length(); i++){
            //System.out.println(i + " - " + s.charAt(i));
            if(k.charAt(i)==' '){
                j++;
                res += ' ';
            }
            else if(k.charAt(i%k.length()) >= 'a' && k.charAt((i-j)%k.length()) <= 'z'){
                res += CesarChar(s.charAt(i), k.charAt((i-j)%k.length()) - 'a');
            }
            else if(k.charAt(i%k.length()) >= 'A' && k.charAt((i-j)%k.length()) <= 'Z'){
                res += CesarChar(s.charAt(i), k.charAt((i-j)%k.length()) - 'A');
            }
        }
        return res;
    }

    public static String readFile(String filename){
        String res = "";
        try (FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr)
        ) {

            String str;
            while ((str = reader.readLine()) != null) {
                res += str;
            }

        } catch (IOException e) {
            e.printStackTrace();
  }

        return res;
    }

    public static void writeFile(String filename, String s){
        try{
            PrintWriter pw = new PrintWriter(new File(filename));
            pw.println(s);
            pw.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static double[] frequenceLettres(String s){
        double[] res = new double[26];
        //fill res with 0s
        for(int i = 0; i < res.length; i++){
            res[i] = 0;
        }
        //System.out.println(s.length());
        //System.out.println(s);

        for(int i = 0; i < s.length(); i++){
            //System.out.println(s.charAt(i));
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                res[s.charAt(i) - 'a']++;
            }
            else if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                res[s.charAt(i) - 'A']++;
            }
        }
        for(int i = 0; i < res.length; i++){
            //System.out.println(i+" - "+res[i]);
            res[i] /= s.length();
        }
        return res;
    }

    public static int cesarFrequence(String s){
        double[] freq = frequenceLettres(s);
        double min = 100;
        int res = 0;
        double sumFreq = 0;
        double[] frequenceAlphabetFrance = {0.0797, 0.0107, 0.0347, 0.04, 0.179, 0.0101,0.0104,0.0135,0.0734,0.003,0.00069,0.0548,0.0317, 0.0702,0.0527,0.028,0.0113,0.0664,0.0772,0.0728,0.0574,0.0117,0.00059,0.00449,0.003309,0.0004};

        for(int i = 0; i < 26; i++){
            freq = frequenceLettres(Cesar(s, i));
            sumFreq = 0;
            for(int j = 0; j < freq.length; j++){
                sumFreq += Math.pow(freq[j] - frequenceAlphabetFrance[j], 2);
            }
            if (min> sumFreq){
                min = sumFreq;
                res = i;
                //System.out.println(i+" - "+sumFreq);
            }
        }
        return res;
    }

    public static double indiceCoincidence(String s,int p){
        double[] count = new double[26];
        String text = "";
        double res = 0;
        double temp = 0;
        int n =0;
        for(int q = 0; q<p; q++){
            n=0;
            for(int j=0; j<s.length(); j++){
                if((j-n)%p == q){
                    text += s.charAt(j);
                }
                if(s.charAt(j) == ' '){
                    n++;
                }
            }

            for(int i = 0; i < count.length; i++){
                count[i] = 0;
            }
    
            for(int i = 0; i < text.length(); i++){
                if(text.charAt(i) >= 'a' && text.charAt(i) <= 'z'){
                    count[text.charAt(i) - 'a']++;
                }
                else if(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z'){
                    count[text.charAt(i) - 'A']++;
                }
            }    
            temp =0;
            for (int i = 0; i < count.length; i++){
                temp += count[i] * (count[i] - 1);
            }
            res = temp/(text.length()*(text.length()-1));
        }
        return res/p;
    }        

    public static int indexVigenere(String s){
        double goal = 0.0745;
        double indice = indiceCoincidence(s, 1);
        double indicePre = indiceCoincidence(s,1);
        if(indiceCoincidence(s,1)>=0.8*goal){
            return 1;
        }
        for(int i = 2; i < 32; i++){
            indicePre = indice;
            indice = indiceCoincidence(s,i);
            //System.out.println(indice+" - "+indicePre);
            if(indice>=goal){
                return i;
            }
            if(indice >= 1.5*indicePre){
                return i;
            }
        }
        return 0;
    }

    public static String solveVigenere(String s){
        int nb = indexVigenere(s);
        System.out.println(nb);
        String res = "";
        String ss = "";
        for(int i = 0; i < nb; i++){
            ss= "";
            for(int j=0; j<s.length(); j++){
                if(j%nb == i){
                    ss += s.charAt(j);
                }
            }
            res += (char)(cesarFrequence(ss)+'a');
        }
        return res;
    }
}
