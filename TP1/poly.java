package TP1;

import java.util.Scanner;

public class poly {
    public double[] coefs = new double[0];
    public int degre = 0;

    public poly(double[] coefs) {
        this.coefs = coefs;
        this.degre = coefs.length-1;
    }

    public poly(){
        System.out.println("Veuillez entrer le degré du polynome");
        try (Scanner sc = new Scanner(System.in)) {
            this.degre = sc.nextInt();
            this.coefs = new double[this.degre+1];
            for(int i = 0; i < this.degre+1; i++){
                System.out.println("Veuillez entrer le coefficient de degré " + i);
                this.coefs[i] = sc.nextLong();
            }
        }
    }

    public void printInfo(){
        System.out.println("Le polynome est de degré " + this.degre);
        for(int i = 0; i < this.degre+1; i++){
            System.out.println("Le coefficient de degré " + i + " est " + this.coefs[i]);
        }
    }

    public String toString(){
        String res = "Le polynôme est P(x) = ";
        for(int i = this.degre; i >= 0; i--){
            if(i == 0){
                res += this.coefs[i];
            }
            else if(i == 1 && this.coefs[i] != 0){
                res += this.coefs[i] + "x + ";
            }
            else{
                if(coefs[i] != 0){
                    res += this.coefs[i] + "x^" + i + " + ";
                }
            }
        }
        return res;
    }

    public double Val(double x){
        double res = 0;
        for(int i = 0; i < this.degre+1; i++){
            res += this.coefs[i] * Math.pow(x, i);
        }
        return res;
    }

    public double ValHornerIterative(double x){
        double res = this.coefs[this.degre];
        for(int i = this.degre-1; i >= 0; i--){
            res = res * x + this.coefs[i];
        }
        return res;
    }

    public double ValHornerRecursive(double x){
        return this.ValHornerRecursive(x, 0);
    }

    public double ValHornerRecursive(double x, int degre){
        if(degre == this.coefs.length-1){
            return this.coefs[this.coefs.length-1];
        }
        else{
            return this.ValHornerRecursive(x, degre+1) * x + this.coefs[degre];
        }
    }

   

    public poly addPolyA(poly poly1){
        double[] coefs;
        if(poly1.coefs.length >= this.coefs.length){
            coefs = new double[poly1.coefs.length];
            for (int i = 0; i < poly1.coefs.length; i++) {
                if(i < this.coefs.length){
                    coefs[i] = poly1.coefs[i] + this.coefs[i];
                }
                else{
                    coefs[i] = poly1.coefs[i];
                }
            }
        }
        else{
            coefs = new double[this.coefs.length];
            for (int i = 0; i < this.coefs.length; i++) {
                if(i < poly1.coefs.length){
                    coefs[i] = poly1.coefs[i] + this.coefs[i];
                }
                else{
                    coefs[i] = this.coefs[i];
                }
            }
        }
        return new poly(coefs);
    }

    public poly produitExt(double facteur){
        double[] coefs = new double[this.degre+1];
        for(int i = 0; i < this.degre+1; i++){
            coefs[i] = this.coefs[i] * facteur;
        }
        return new poly(coefs);
    }

    public poly derivePoly(){
        double[] coefs = new double[this.degre];
        for(int i = 0; i < this.degre; i++){
            coefs[i] = this.coefs[i+1] * (i+1);
        }
        return new poly(coefs);
    }

    public poly primitivePoly(){
        double[] coefs = new double[this.degre+2];
        for(int i = 0; i < this.degre+2; i++){
            if(i == 0){
                coefs[i] = 0;
            }
            else{
                coefs[i] = this.coefs[i-1] / i;
            }
        }
        return new poly(coefs);
    }

    public double dicho(double a, double b, double epsilon){
        double m = (a+b)/2;
        if(Math.abs(this.Val(m)) < epsilon){
            return m;
        }
        else if(this.Val(a) * this.Val(m) < 0){
            return dicho(a, m, epsilon);
        }
        else{
            return dicho(m, b, epsilon);
        }
    }

    public double newton(double x0, double epsilon){
        double x1 = x0 - this.Val(x0) / this.derivePoly().Val(x0);
        if(Math.abs(x1 - x0) < epsilon){
            return x1;
        }
        else{
            return newton(x1, epsilon);
        }
    }

    public poly produitPoly(poly poly1){
        double[] coefs = new double[this.degre + poly1.degre + 1];
        for(int i = 0; i < this.degre + poly1.degre + 1; i++){
            coefs[i] = 0;
        }
        for(int i = 0; i < this.degre+1; i++){
            for(int j = 0; j < poly1.degre+1; j++){
                coefs[i+j] += this.coefs[i] * poly1.coefs[j];
            }
        }
        return new poly(coefs);
    }

}
