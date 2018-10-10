import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;

public class checkTopological{
    
    private boolean isBackwardsEdge;

    public checkTopological(Digraph G, int [] order){
        
        //order[] is a given array of vertices from G. We will check if the order they're given in is a topological sort
        //We are going to iterate through the order array
        
        //A backwards edge is found when a vertex has an edge with any of its predecessors in the order
        for(int i = order.length-1;i>0;i--){
            
	    //We stop the search as soon as the first backwards edge is found.
            if(isBackwardsEdge==true){
                break;
            }
            for(int j = i-1;j>=0;j--){
                for(int w : G.adj(order[i])){
                    if(w==order[j]){
                        isBackwardsEdge=true;
                        break;
                    }   
                }
            }
        }
    }
    
    public boolean isSort(){
        
        return !isBackwardsEdge;
    }
    
    public static void main (String[] args){
        
        Digraph G = new Digraph(3);
        G.addEdge(0,1);
        G.addEdge(1,2);
        int [] order = {2,1,0};
        checkTopological top = new checkTopological(G,order);
        //This is not a sort so it should return false
        System.out.println(top.isSort());
        
        //This is a sort so it should return true
        int [] order1 = {0,1,2};
        checkTopological top1 = new checkTopological(G,order1);     
        System.out.println(top1.isSort());
        
    }
        
        
}
                        

    
    