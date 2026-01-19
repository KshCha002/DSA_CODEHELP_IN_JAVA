class Solution {
    public void reverseString(char[] s) {
        int n =s.length;
        int i=0;
        int j=n-1;
        char t =s[0];
         while(i<j)
         {
            t=s[i];
            s[i]=s[j];
            s[j]=t;
            i++;
            j--;
         }
    }
}