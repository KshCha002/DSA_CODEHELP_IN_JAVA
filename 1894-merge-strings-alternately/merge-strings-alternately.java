class Solution {
    public String mergeAlternately(String word1, String word2) {
        //two pointer
        //while loop add from1 and word 2
        //if 1 finshes early add rest from 2 vice versa
        int n1=word1.length();
        int n2=word2.length();
        String str="";
        int i=0;int j=0;
        while(i<n1 && j<n2){
            char ch1=word1.charAt(i);
            char ch2=word2.charAt(j);
            str=str+ch1+ch2;
            i++;
            j++;
        }
          while(i<n1){
            char ch1=word1.charAt(i);
            str=str+ch1;
            i++;
        }
          while( j<n2){
            char ch2=word2.charAt(j);
            str=str+ch2;
            j++;
        }
return str;

    }
}