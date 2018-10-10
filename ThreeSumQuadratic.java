import java.util.Arrays;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
public class ThreeSumQuadratic{
    
    public static void main(String[] args){
        
        Stopwatch timer = new Stopwatch();
        In in = new In("http://algs4.cs.princeton.edu/14analysis/8Kints.txt");
        int [] a = in.readAllInts();
        Arrays.sort(a);
        int length = a.length;
        int count = 0;
        for(int i = 0; i<length-3; i++){
            int j = i+1;
            int k = length-1;
            while(j<k){
                if(a[i]+a[j]+a[k]==0){
                    count++;
                    k = k-1;
                    }    
                else if(a[i]+a[j]+a[k]>0){
                    k = k - 1;
                }
                else{
                    j=j+1;
                }
            }
        }
        System.out.println(count);
        System.out.println(timer.elapsedTime());
    }
}
                    