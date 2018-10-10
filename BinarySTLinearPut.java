public class BinarySTLinearPut<Key extends Comparable<Key>, Value{
    
    private Key[] keys;
    private Value[] vals;
    private int n;
    
    
    public BinarySTLinearPut(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    
    //We need the resize function from Chapter 1 to grow and shrink the array
    //as needed in put function.
    private void resize(int max){
        Item[] temp = (Item[]) new Comparable[max];
        Item[] temp2 = (Item[]) new Object[max];
        for (int i = 0;i<n;i++){
            temp[i]=keys[i];
            temp2[i]=vals[i];
        }
        keys = temp;
        vals = temp2;
    }
    
    public void put(Key key, Value val){
        //This makes sure we have space in the keys and values arrays
        if(n==capacity){
            resize(2*capacity);
        }  
        //This checks if the key we're entering into the ordered array is larger than the last key.
        //If it is we insert it at the end. Change the keys and values arrays and increase size by 1.
        //This bypasses the rank function which works in nlogn time, and lets us add values in constant time.
        //This works assuming the keys we are inserting are all in order.
        if(key>key[n-1]){
            key[n]=key;
            vals[n]=val;
            n++;
        }
        //The rest of the put function if keys are not in order
        int i = rank(key);
        if(i<n && keys[i].compareTo(key)==0){
            vals[i] = val;
            return;
        }
        for(int j =n; j>i; j--){
            keys[j]=keys[j-1];
            vals[j]=vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }
}
        
        
    
            