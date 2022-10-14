package TP1;

import java.util.Scanner;

public class polyCreux {
    public double[][] coefs;
    public int degre;

    public polyCreux(double[][] coefs){
        this.coefs = coefs;
        this.degre = coefs.length-1;
    }

    public polyCreux(){
        System.out.println("Veuillez entrer le degré du polynome");
        Scanner sc = new Scanner(System.in);
        this.degre = sc.nextInt();
        this.coefs = new double[2][this.degre+1];
        int j = 0;
        for(int i = 0; i < this.degre+1; i++){
            System.out.println("Veuillez entrer le coefficient de degré " + i);
            this.coefs[1][j] = sc.nextLong();
            if(this.coefs[1][j] != 0){
                this.coefs[0][j] = i;
                this.coefs[1][j] = this.coefs[1][j];
                j++;
            }
        }
        sc.close();
        double[][] res = new double[2][j];
        for(int i = 0; i < j; i++){
            res[0][i] = this.coefs[0][i];
            res[1][i] = this.coefs[1][i];
        }
        this.coefs = res;
    }

    public String toString(){
        String res = "Le polynôme est P(x) = ";
        for(int i = 0; i < this.coefs[0].length; i++){
            if(this.coefs[0][i] == 0){
                res += this.coefs[1][i];
            }
            else if(this.coefs[0][i] == 1){
                res += this.coefs[1][i] + "x + ";
            }
            else{
                res += this.coefs[1][i] + "x^" + this.coefs[0][i] + " + ";
            }
        }
        return res;
    }
}
