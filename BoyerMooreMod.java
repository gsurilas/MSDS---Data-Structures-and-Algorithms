public class BoyerMooreMod{
 
    private int[] right;     // the bad-character skip array
    private String pat;      // or as a string

    BoyerMooreMod(String pat) {
        
        this.pat = pat;
        int m = pat.length();
        int R = 256;
        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return n;                       
    }
    
    //Same code as search except it returns a count value for the number of pattern occurences
    //in the string
    public int count(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        int count = 0;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            //Increment i so that we don't get stuck in an infinite loop trying to increment by skip=0
            if (skip == 0){ 
                count++;    // found
                i++;
                continue;
            }
        }
        return count;                       
    }
    
    public static void main(String[] args){
        
        String pat = "hippie";
        String txt = "hihohippiehohippiehohi";
        BoyerMooreMod bm = new BoyerMooreMod(pat);
        System.out.println(bm.count(txt));
    }
}
        

