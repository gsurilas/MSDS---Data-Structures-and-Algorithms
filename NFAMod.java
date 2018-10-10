import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedDFS;

public class NFAMod{ 

    private Digraph graph;     // digraph of epsilon transitions
    private char[] re;         // regular expression
    private final int m;       // number of characters in regular expression

    public NFAMod(String regexp) {
        re = regexp.toCharArray();
        m = regexp.length();
        Stack<Integer> ops = new Stack<Integer>();
        Queue<Integer> ors = new Queue<Integer>();
        graph = new Digraph(m+1); 
        for (int i = 0; i < m; i++) { 
            int lp = i; 
            if (re[i] == '(' || re[i] == '|') 
                ops.push(i); 
            //Multiway OR Section
            else if (re[i] == ')') {
                int or = ops.pop();
                //Find the first '|'. Build an edge from it to the ')'. And add the '|' to the queue.
                if (re[or] == '|') { 
                    graph.addEdge(or,i);
                    ors.enqueue(or);
                    //Pop the next item off the stack. If it's also an '|'. Add it to the queue and draw
                    //an edge from the '|' to ')'. Keep popping off the stack until you get to an '('.
                    int prev = ops.pop();
                    while(re[prev] == '|'){
                        ors.enqueue(prev);
                        graph.addEdge(prev,i);
                        prev = ops.pop();
                    }
                    //First while loop ends when a left parantheses is encountered. 
                    //Now go through the queue of '|' and draw an edge from the left parantheses to the '|'+1 indices.
                    while(!ors.isEmpty()){
                        or = ors.dequeue();
                        graph.addEdge(prev,or+1);
                    }
                }
                else if (re[or] == '(')
                    lp = or;
                else assert false;
            } 

            // closure operator (uses 1-character lookahead)
            if (i < m-1 && re[i+1] == '*') { 
                graph.addEdge(lp, i+1); 
                graph.addEdge(i+1, lp); 
            } 
            if (re[i] == '(' || re[i] == '*' || re[i] == ')') 
                graph.addEdge(i, i+1);
        }
        if (ops.size() != 0)
            throw new IllegalArgumentException("Invalid regular expression");
    }
    
    public boolean recognizes(String txt) {
        DirectedDFS dfs = new DirectedDFS(graph, 0);
        Bag<Integer> pc = new Bag<Integer>();
        for (int v = 0; v < graph.V(); v++)
            if (dfs.marked(v)) pc.add(v);

        // Compute possible NFA states for txt[i+1]
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '*' || txt.charAt(i) == '|' || txt.charAt(i) == '(' || txt.charAt(i) == ')')
                throw new IllegalArgumentException("text contains the metacharacter '" + txt.charAt(i) + "'");

            Bag<Integer> match = new Bag<Integer>();
            for (int v : pc) {
                if (v == m) continue;
                if ((re[v] == txt.charAt(i)) || re[v] == '.')
                    match.add(v+1); 
            }
            dfs = new DirectedDFS(graph, match); 
            pc = new Bag<Integer>();
            for (int v = 0; v < graph.V(); v++)
                if (dfs.marked(v)) pc.add(v);

            // optimization if no states reachable
            if (pc.size() == 0) return false;
        }

        // check for accept state
        for (int v : pc)
            if (v == m) return true;
        return false;
    }
    
    public static void main(String[] args){
        
        NFAMod nfa = new NFAMod("(.*AB((C|D|E)F)*G)");
        //Test if the multiway or is working. It is.
        System.out.println(nfa.recognizes("ABCFG"));
        System.out.println(nfa.recognizes("ABDFG"));
        System.out.println(nfa.recognizes("ABEFG"));
        
    }
                                
}