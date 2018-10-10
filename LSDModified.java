import edu.princeton.cs.algs4.LSD;
import java.util.Arrays;

public class LSDModified{
    
    private LSDModified(){}
    
    
    //Sort the strings by length using key indexed counting so you produce an array with shortest lengths at the beginning
    //and longest lengths at the end of the array
    public static String[] sortByLength(String[] a) {
        int n = a.length;
        int R = longest(a)+1;   
        String[] aux = new String[n];
        int[] count = new int[R+1];

            // compute frequency counts
            for (int i = 0; i < n; i++)
                count[a[i].length() + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];
            
            // move data
            for (int i = 0; i < n; i++)
                aux[count[a[i].length()]++] = a[i];

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
 
            
            return a;  
        }
    
    //Calculate the length of the longest string in array so we know how large to make the count array
    //in the sortByLength method.
    private static int longest(String[] a){
        
        int longest = 0;
        for(int i = 0; i<a.length; i++){
            if(a[i].length()>longest){
                longest = a[i].length();
            }
        }
        return longest;
    }


    public static void main(String[] args){
        
        String [] a = {"597","432","65781","145","136","289761","236896","241","12","123","431"};
        String [] b = LSDModified.sortByLength(a);
        for(int i =0;i<b.length;i++){
            System.out.println(b[i]);
            
        }
        





        
    }
}