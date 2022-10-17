package TP3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TP3 {
    public static void main(String[] args){
        String filename = "TP3";

        System.out.println(Cesar("Vous faites un TP d'informatique en ce moment", 5));
        System.out.println(Vigenere("Vous faites un TP d'informatique en ce moment", "TPFI"));
        writeFile(filename+"-e.txt", Vigenere(readFile(filename+".txt"), "TPFI"));
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
        for(int i = 0; i < s.length(); i++){
            if(k.charAt(i%k.length()) >= 'a' && k.charAt(i%k.length()) <= 'z'){
                res += CesarChar(s.charAt(i), k.charAt(i%k.length()) - 'a');
            }
            else if(k.charAt(i%k.length()) >= 'A' && k.charAt(i%k.length()) <= 'Z'){
                res += CesarChar(s.charAt(i), k.charAt(i%k.length()) - 'A');
            }
        }
        return res;
    }

    public static String readFile(String filename){
        String res = "";
        try{
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNextLine()){
                res += sc.nextLine();
            }
            sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
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




}
