import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;


public class Topological {
    private Iterable<Integer> order;  // topological order
    private int[] rank;               // rank[v] = rank of vertex v in order
    
    public Topological(Digraph G) {
        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }
    
    //HOMEWORK 7 PART 2
    //Add a method in Topological to determine if a Topological sort is unique
    public boolean isUnique(Digraph G){
        
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
            return isUnique;
        }


    public Iterable<Integer> order() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }

    @Deprecated
    public boolean isDAG() {
        return hasOrder();
    }
    
    public static void main (String[] args){
        
        Digraph G = new Digraph(6);
        G.addEdge(5,0);
        G.addEdge(5,2);
        G.addEdge(2,3);
        G.addEdge(3,1);
        G.addEdge(4,0);
        G.addEdge(4,1);
        Topological top = new Topological(G);
        //G has two possible sorts 542310 and 452310 so isUnique should return false
        System.out.println(top.isUnique(G));
        
        Digraph H = new Digraph(3);
        H.addEdge(0,1);
        H.addEdge(1,2);
        //H has one possible sort so isUnique should return true
        Topological top1 = new Topological(H);
        System.out.println(top1.isUnique(H));
        


                                

        
        
    }






}
    
    