import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Quick;
import java.util.*;

public class duplicateFinder{
    
    public static Integer[][] create(int k, int N){
        
        int size = N/k;
        Integer [][] arr = new Integer[k][size];
        for(int i =0; i < k; i++){
            for(int j = 0; j<size; j++){
                arr[i][j] = StdRandom.uniform(0,100);
            }
        }

        return arr;
    }
    public static boolean Finder(Integer [][] x){
        
        boolean duplicates = false;
        int size = x.length *x[0].length;
        Integer [] arr = new Integer[size];
        int i = 0;
        for(int j = 0; j<x.length;j++){
            for(int k =0; k< x[j].length;k++){
                arr[i++] = x[j][k];
                }
            }

        Quick.sort(arr);
        for(i=0;i<size;i++){
            System.out.print(arr[i] + " ");
        }
        
        int k = 0;
        while(k<size){
            if(arr[k]==arr[k+1]){
                duplicates = true;
                break;
            }
            else{
                k++;
            }
        }
        return duplicates;
    }
        
    


    
    public static void main(String[]args){
        
        Integer [][] x = create(2,50);
        for(int i =0; i<x.length;i++){
            for(int j=0;j<x[i].length;j++){
                System.out.print(x[i][j] +" ");
            }
            System.out.println();
        }
        boolean y = Finder(x);
        System.out.println(y);
    }
}
            

