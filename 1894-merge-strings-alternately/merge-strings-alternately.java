class Solution {
    public String mergeAlternately(String word1, String word2) {
         int n1= word1.length();
         int n2=word2.length();
         int i=0;
         int j=0;
         String str= "";
         while(i<n1 && j<n2 ){
            str = str + word1.charAt(i);
            str = str + word2.charAt(j);
            i++;
            j++;
        }
        if(i==n1 & j==n2)
        {
            System.out.println("Early");
            return str;
        }
         while(i<n1  ){
            str = str + word1.charAt(i);
           
            i++;
            
        }
         while(j<n2 ){
            
            str = str + word2.charAt(j);
          
            j++;
        }
    return str;
    
        }
}