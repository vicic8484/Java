package TP2;

public class TP2 {
    //write hello wolrd to console
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(54));
        System.out.println(Integer.toHexString(54));

        double nb = 0.5;
        long doubleAsLong = Double.doubleToLongBits(nb);
        System.out.println(Long.toHexString(doubleAsLong));
        System.out.println(Long.toBinaryString(doubleAsLong));

        fizzbuzz(20);
        System.out.println(fizzbuzzString(20));

        //tables();
        tablesTableau();

        System.out.println(piViete(0.001));
        //System.out.println(Math.PI);
        System.out.println((operateurFleche(3,2,3)));

        Hanoi(3, 'A', 'C', 'B');

        decimalToHex(54);
    }

    public static String toHexString(String n){
        String res = "";
        for(int i = 0; i < n.length(); i++){
            res += Integer.toHexString(n.charAt(i));
        }
        return res;
    }

    public static void fizzbuzz(int n){
        for(int i = 1; i <= n; i++){
            if(i%3 == 0 && i%7 == 0){
                System.out.print("FizzBuzz ");
            }
            else if(i%7 == 0){
                System.out.print("Fizz ");
            }
            else if(i%5 == 0){
                System.out.print("Buzz ");
            }
            else{
                System.out.print(i+" ");
            }
            if(i<n){
                System.out.print("- ");
            }
        }
        System.out.println();
    }

    public static String fizzbuzzString(int n){
        String res = "";
        for(int i = 1; i <= n; i++){
            if(i%3 == 0 && i%7 == 0){
                res += "FizzBuzz ";
            }
            else if(i%7 == 0){
                res += "Fizz ";
            }
            else if(i%5 == 0){
                res += "Buzz ";
            }
            else{
                res += i+" ";
            }
            if(i<n){
                res += "- ";
            }
        }
        return res;
    }

    public static void tables(){
        for(int i = 1; i <= 10; i++){
            System.out.println("Table de multiplication de "+i+" :");
            for(int j = 1; j <= 10; j++){
                System.out.println(i+" x "+j+" = "+i*j);
            }
            System.out.println();
        }
    }

    public static void tablesTableau(){
        int[][] tab = new int[10][10];
        for(int i = 0; i < 10; i++){
           tab[i][0] = i;
           tab[0][i] = i;
        }
        for(int i = 1; i < 10; i++){
            for(int j = 1; j < 10; j++){
                tab[i][j] = tab[i][0] * tab[0][j];
            }
        }
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.format("%2d ",tab[i][j]);
            }
            System.out.println();
        }
    }

    public static void tables(int min, int max){
        for(int i = min; i <= max; i++){
            System.out.println("Table de multiplication de "+i+" :");
            for(int j = 1; j <= 10; j++){
                System.out.println(i+" x "+j+" = "+i*j);
            }
            System.out.println();
        }
    }

    public static void tablesTableau(int min, int max){
        int[][] tab = new int[max-min+1][10];
        for(int i = 0; i < max-min+1; i++){
           tab[i][0] = i+min;
           tab[0][i] = i+min;
        }
        for(int i = 1; i < max-min+1; i++){
            for(int j = 1; j < 10; j++){
                tab[i][j] = tab[i][0] * tab[0][j];
            }
        }
        for(int i = 0; i < max-min+1; i++){
            for(int j = 0; j < 10; j++){
                System.out.format("%2d ",tab[i][j]);
            }
            System.out.println();
        }
    }

    public static void decimalToHex(int n){
        String res = "";
        while(n > 0){
            int r = n%16;
            if(r < 10){
                res = r + res;
            }
            else{
                res = (char)(r+55) + res;
            }
            n = n/16;
        }
        System.out.println("0x"+res);
    }

    public static String decimalToHexString(int n){
        String res = "";
        while(n > 0){
            int r = n%16;
            if(r < 10){
                res = r + res;
            }
            else{
                res = (char)(r+55) + res;
            }
            n = n/16;
        }
        return res;
    }

    public static double piViete(double presition){
        double res = Math.sqrt(2);
        double div = Math.sqrt(2);
        while(Math.abs((res*2)-Math.PI)>presition){
            div = Math.sqrt(2+div);
            res *= 2/div;
        }
        return res*2;
    }

    public static int HanoiNum(int n){
        if(n == 1){
            return 1;
        }
        else{
            return 2*HanoiNum(n-1)+1;
        }
    }

    public static void Hanoi(int n, char depart, char inter, char arrivee){
        if(n == 1){
            System.out.println("Déplacer le disque "+n+" de "+depart+" vers "+arrivee);
        }
        else{
            Hanoi(n-1,depart,arrivee,inter);
            System.out.println("Déplacer le disque "+n+" de "+depart+" vers "+arrivee);
            Hanoi(n-1,inter,depart,arrivee);
        }
    }

    public static double operateurFleche(double m, int k, double n){
        if(k == 1){
            return Math.pow(m,n);
        }
        else{
            if(n == 1){
                return m;
            }
            else{
                double res = m;
                for(int i = 1; i < n; i++){
                    res = Math.pow(m,res);
                }
                return res;
            }
        }
    }
}
