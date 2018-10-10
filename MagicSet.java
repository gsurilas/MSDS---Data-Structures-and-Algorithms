public class MagicSet
{
    private int[] a;
    private int next = 0;
    
    //MagicSet constructor
    public MagicSet(){
        a = new int[1];
    }
    
    //resizes the array to designated size
    private void resize(int size){
        int[] temp = new int[size];
        for(int i = 0; i<a.length;i++){
            temp[i] = a[i];
        }
        a = temp;
    }
    
    //adds designated value to the magicSet and doubles the array size using resize Method
    //if array is full
    public void addValue(int value){
        if(a.length == next){
            resize(2*a.length);
        }
        a[next] = value;
        next++;
    }
    
    //Performs linear search through magicSet to determine if it contains a specific value
    public boolean contains(int value){
        boolean contains = false;
        int pos = 0;
        while(pos<a.length && !contains){
            if(a[pos] == value){
                contains = true;
            }
            else{
                pos++;
            }
        }
        return contains;
    }
    
    //prints out the array contents on one line (includes initialized '0' elements)
    public void tostring(){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i] +" ");
            
        }
        System.out.println();
    }
    
    //Combines a magicSet with an int array and produces a new magicSet. Couldn't figure out
    //how to make the parameter input a magicSet instead of an int array.
    public MagicSet union(int[] b){
        MagicSet c = new MagicSet();
        for(int i=0;i<a.length;i++){
            c.addValue(a[i]);
        }
        for(int i=0;i<b.length;i++){
            c.addValue(b[i]);
        }
        return c;
    }
    
    public void deleteValue(int value){
        if(next>0 && next==a.length/4){
            resize(a.length/2);
        }
        for(int i=0;i<a.length;i++){
            if(a[i]==value){
                a[i]=a[i+1];
                next--;
                
            }
            
        }
    }
      
    
        
        //contstructor test
        MagicSet s = new MagicSet();
        //add values test
        System.out.println("Here we add values 5,6,7");
        s.addValue(5);
        s.addValue(6);
        s.addValue(7);
        s.tostring();
        //Delete Values Test
        System.out.println("Here we delete 7");
        s.deleteValue(7);
        s.tostring();
        //Contains Test
        System.out.println("Here we test whether the set contains 11");
        boolean x = s.contains(11);
        System.out.println(x);
        //Union Test
        System.out.println("Here we join MagicSet s to y to create a new Magic Set z");
        int [] y = {11,12,13};
        MagicSet z = s.union(y);
        z.tostring();
            



        

        

    }
}