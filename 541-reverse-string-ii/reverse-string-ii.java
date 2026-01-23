class Solution {
    public String reverseStr(String s, int k) {
        int n=s.length();
        int i=0;
        char[] ch = s.toCharArray();
        
        while(i<n)
        {
          int j = Math.min(i + k - 1 , n - 1);
           ch= reverseKChar(ch,i,j);
            i += 2 * k;
        }
        return new String(ch);

    }
    public char[] reverseKChar(char[] s,int i,int j) {
        int n =s.length;
        char t =s[0];
         while(i<j)
         {
            t=s[i];
            s[i]=s[j];
            s[j]=t;
            i++;
            j--;
         }
         return s;
    }
}