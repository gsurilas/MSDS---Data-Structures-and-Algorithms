public class KMPmod{
    
    private String pat;
    private int[][] dfa;
    
    public KMPmod(String pat){
        this.pat=pat;
        int m = pat.length();
        int R = 256;
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0]=1;
        for(int x=0,j=1;j<m;j++){
            for(int c = 0; c<R; c++)
                dfa[c][j] = dfa[c][x];
            dfa[pat.charAt(j)][j] = j+1;
            x = dfa[pat.charAt(j)][x];
        }
    }
    
    public int search(String txt){
        
        int i,j,n = txt.length();
        int m = pat.length();
        for(i=0,j=0;i<n && j<m;i++){
            j = dfa[txt.charAt(i)][j];
        }
        if(j==m) return i-m;
        else return n;
    }
    
    public int count(String txt){
        
        int i;
        int j;
        int n = txt.length();
        int m = pat.length();
        int count = 0;
        i=0;
        j=0;
        do{
            j = dfa[txt.charAt(i)][j];
            i++;
            //When final state is reached, increment count and reset
            //j to 0, for initial state.
            if(j==m){
                count++;
                i++;
                j=0;
            }
        }
        while(i<n);
        return count;

    }

    public static void main (String[] args){
        
        String pat = "hi";
        String txt = "hihohihohiho";
        KMPmod kmp = new KMPmod(pat);
        System.out.println(kmp.count(txt));
    }
            
}
        
                    