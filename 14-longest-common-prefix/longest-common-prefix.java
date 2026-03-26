class Solution {
    public String longestCommonPrefix(String[] v) {
       String ans="";
       Arrays.sort(v);

    String s1= v[0];
    String s2= v[v.length-1];
    if(s1.length()<s2.length())
    {
    for(int i=0;i<s1.length();i++)
    {
    if( s1.charAt(i)!=  s2.charAt(i))
    {
        return ans;
    }else
    {
        ans=ans+ s1.charAt(i);
    }
    }

    }else
    {
        
    for(int i=0;i<s2.length();i++)
    {
    if(s1.charAt(i)!=  s2.charAt(i))
    {
        return ans;
    }else
    {
        ans=ans+ s1.charAt(i);
    }
    }

    
    }
    return ans;
    
    }
    
}