package TP2;

public class TP2 {
    //write hello wolrd to console
    public static void main(String[] args) {
        System.out.println(toBinaryString(54));
        System.out.println(toHexString(54));

        double nb = 0.5;
        long doubleAsLong = Double.doubleToLongBits(nb);
        System.out.println(Long.toHexString(doubleAsLong));
        System.out.println(Long.toBinaryString(doubleAsLong));

        fizzbuzz(20);
        System.out.println(fizzbuzzString(20));

        //tables();
        tablesTableau();
    }

    public static String toBinaryString(int n){
        String res = "";
        while(n > 0){
            res = n%2 + res;
            n = n/2;
        }
        return res;
    }

    public static String toBinaryString(Byte n){
        int m = n;
        String res = "";
        while(n > 0){
            res = m%2 + res;
            m = m/2;
        }
        return res;
    }

    public static String toBinaryString(long n){
        String res = "";
        while(n > 0){
            res = n%2 + res;
            n = n/2;
        }
        return res;
    }

    public static String toHexString(int n){
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

    public static String toHexString(Byte m){
        int n = m;
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

    public static String toHexString(long n){
        String res = "";
        while(n > 0){
            long r = n%16;
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

    public static String toHexString(String n){
        String res = "";
        for(int i = 0; i < n.length(); i++){
            res += toHexString(n.charAt(i));
        }
        return res;
    }

    public static Long doubleToRawLongBits(double n){
        return Double.doubleToRawLongBits(n);
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

}
