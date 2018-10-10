public class checkRotation{

    public static boolean checkRotation(String s1, String s2){
        
        if(s1.equals(s2)){
            return false;
        }

        String s3 = s1.concat(s1);
        if(s3.contains(s2)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void main(String[]args){
        
        String s1 = "abc";
        String s2 = "bca";
        String s3 = "abc";
        String s4 = "cab";
        System.out.println(checkRotation(s1,s2));
        System.out.println(checkRotation(s1,s3));
        System.out.println(checkRotation(s3,s4));
    }
                           
                           
                           
}