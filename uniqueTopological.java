import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;

public class uniqueTopological{
    
    private Iterable<Integer> order;
    private boolean isUnique;
    
    public uniqueTopological(Digraph G){
        //Get the topological order if it exists
        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
        
        int [] ordered = new int[G.V()];
        
        int i = 0;
        //put the values of the Order iterator object into an array for easier manipulation
        for(int w : order){
            ordered[i] = w;
            i++;
        }
        
        //Initialize a boolean to check uniqueness of sort
        boolean isUnique = true;
        
        //We don't check the last value in the array since it won't have any vertices in its adjacency list.
        
        for(int j = 0;j<ordered.length-1;j++){
            
            if(isUnique==false){
                break;
            }
            /*Look through the adjacency lists of each array value in 'ordered'. Check if the 
            'j+1' array value is contained in the 'jth' adjacency list. If it is, the topological sort
            might still be unique. If it is not in the adjacency list then the sort is not unique. */
            
            for(int w : G.adj(ordered[j])){
               if(w==ordered[j+1]){                   
                    //If we find the the 'j+1' value contained in the adjacency list we can break out of the inner loop
                    //and continue with the following array value.
                    isUnique=true;
                    break;
                    }
               else{
                    //have to keep searching through whole adjacency list even if a false value is found. Because
                    //the adjacency list is unordered and the value we're searching for could be anywhere
                    isUnique = false;
                    }
                }
            }
        }
    
    public boolean isUnique(){
        return isUnique;
    }
    
    public static void main (String[] args){
        
        Digraph G = new Digraph(6);
        G.addEdge(5,0);
        G.addEdge(5,2);
        G.addEdge(2,3);
        G.addEdge(3,1);
        G.addEdge(4,0);
        G.addEdge(4,1);
        uniqueTopological top = new uniqueTopological(G);
        //G has two possible sorts 542310 and 452310 so isUnique should return false
        System.out.println(top.isUnique());
        
        Digraph H = new Digraph(3);
        H.addEdge(0,1);
        H.addEdge(1,2);
        //H has one possible sort so isUnique should return true
        uniqueTopological top1 = new uniqueTopological(H);
        System.out.println(top1.isUnique());
    }
}