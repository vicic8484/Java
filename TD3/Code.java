package TD3;

import java.util.Random;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * @author: Victor Berhault
 */
public class Code {
    
    public static void  main(String[] args){
        NumberFormat nf = NumberFormat.getInstance(new Locale("fr", "FR"));
        nf.setGroupingUsed(true);
        
        int[] data = new int[100000];
        FillArray(data,100000);
        double startTime = System.nanoTime();
        TriInsertion(data);
        double endTime = System.nanoTime();
        System.out.println("Tri Insertion a pris " + nf.format(endTime - startTime) + " nanoseconds ou " + nf.format((endTime - startTime)/1000000000) + " seconds");

        FillArray(data,100000);
        startTime = System.nanoTime();
        TriBulle(data);
        endTime = System.nanoTime();
        System.out.println("Tri Bulle a pris " + nf.format(endTime - startTime) + " nanoseconds ou " + nf.format((endTime - startTime)/1000000000) + " seconds");
        
        FillArray(data,100000);
        startTime = System.nanoTime();
        TriCocktail(data);
        endTime = System.nanoTime();
        System.out.println("Tri Cocktail a pris " + nf.format(endTime - startTime) + " nanoseconds ou " + nf.format((endTime - startTime)/1000000000) + " seconds");
        
        FillArray(data,100000);
        startTime = System.nanoTime();
        TriFusion(data);
        endTime = System.nanoTime();
        System.out.println("Tri Fusion a pris " + nf.format(endTime - startTime) + " nanoseconds ou " + nf.format((endTime - startTime)/1000000000) + " seconds");
    }


    public static void TriInsertion(int[] T){
        int i,j, temp;
        for(i = 1; i < T.length; i++){
            temp = T[i];
            j = i-1;
            while(j >= 0 && T[j] > temp){
                T[j+1] = T[j];
                j--;
            }
            T[j+1] = temp;
        }
    }

    public static void TriSelection(int[] T){
        int i,j, min, temp;
        for(i = 0; i < T.length-1; i++){
            min = i;
            for(j = i+1; j < T.length; j++){
                if(T[j] < T[min]){
                    min = j;
                }
            }
            temp = T[i];
            T[i] = T[min];
            T[min] = temp;
        }
    }

    public static void TriBulle(int[] T){
        int i, temp;
        boolean  sorted = false;
        while(!sorted){
            sorted = true;
            for(i = 0; i < T.length-1; i++){
                if(T[i] > T[i+1]){
                    temp = T[i];
                    T[i] = T[i+1];
                    T[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }

    public static void TriCocktail(int[] T){
        int i, temp;
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for(i = 0; i < T.length-1; i++){
                if(T[i] > T[i+1]){
                    temp = T[i];
                    T[i] = T[i+1];
                    T[i+1] = temp;
                    sorted = false;
                }
            }
            if(!sorted){
                sorted = true;
                for(i = T.length-2; i >= 0; i--){
                    if(T[i] > T[i+1]){
                        temp = T[i];
                        T[i] = T[i+1];
                        T[i+1] = temp;
                        sorted = false;
                    }
                }
            }
        }
    }

    //merge sort
    public static void TriFusion(int[] T){
        int[] temp = new int[T.length];
        TriFusion(T, temp, 0, T.length-1);
    }

    public static void TriFusion(int[] T, int[] temp, int debut, int fin){
        if(debut < fin){
            int milieu = (debut + fin) / 2;
            TriFusion(T, temp, debut, milieu);
            TriFusion(T, temp, milieu+1, fin);
            Fusionner(T, temp, debut, milieu, fin);
        }
    }

    public static void Fusionner(int[] T, int[] temp, int debut, int milieu, int fin){
        int i, gauche, droite, n;
        gauche = debut;
        droite = milieu+1;
        n = fin-debut+1;
        for(i = 0; i < n; i++){
            if(gauche > milieu){
                temp[i] = T[droite];
                droite++;
            }
            else if(droite > fin){
                temp[i] = T[gauche];
                gauche++;
            }
            else if(T[gauche] < T[droite]){
                temp[i] = T[gauche];
                gauche++;
            }
            else{
                temp[i] = T[droite];
                droite++;
            }
        }
        for(i = 0; i < n; i++){
            T[debut+i] = temp[i];
        }
    }


    public static void FillArray(int[] T, int n){
        Random rand = new Random();
        for(int i = 0; i < T.length; i++){
            T[i] = rand.nextInt(n);
        }
    }

    public static void FillSortedArray(int[] T){
        for(int i = 0; i < T.length; i++){
            T[i] = i;
        }

    }

    public static String Strigify(int[] T){
        String json = "[";
        for(int i = 0; i < T.length; i++){
            json += T[i];
            if(i != T.length-1){
                json += ",";
            }
        }
        json += "]";
        return json;
    }

}
